/*
 * Copyright (c) 2024 trycatch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.trycatch.remote.datasource

import com.trycatch.crypto.RpcClient
import com.trycatch.data.datasource.SolanaDataSource
import com.trycatch.data.model.QuoteEntity
import com.trycatch.data.model.TokenEntity
import com.trycatch.remote.CMCApiService
import com.trycatch.remote.IPFSApiService
import com.trycatch.remote.model.TokenBalanceResponse
import com.trycatch.remote.model.TokenResponse
import com.trycatch.remote.model.toResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import javax.inject.Inject

class SolanaDataSourceImpl @Inject constructor(
    private val solanaRpcClient: RpcClient,
    private val ipfsApiService: IPFSApiService,
    private val cmcApiService: CMCApiService
): SolanaDataSource {

    companion object {
        private const val LAMPORTS_PER_SOL = 1_000_000_000
    }

    override suspend fun getBalance(publicKey: String): Result<String> =
        withContext(Dispatchers.IO) {
            solanaRpcClient.getBalance(publicKey).map {
                BigDecimal(it).divide(BigDecimal(LAMPORTS_PER_SOL)).toString()
            }
        }

    override suspend fun getTokens(publicKey: String): Result<List<TokenEntity>> =
        withContext(Dispatchers.IO) {
            solanaRpcClient.getTokenWallets(publicKey).mapCatching { walletList ->
                walletList.map { wallet ->
                    async {
                        runCatching {
                            coroutineScope {
                                val balanceDeferred = async {
                                    getTokenBalance(wallet.publicKey).getOrNull()
                                }
                                val tokenEntityDeferred = async {
                                    solanaRpcClient.findByMint(wallet.tokenAddress)
                                        .map { token ->
                                            val ipfs = getIpfsImage(token.uri)
                                            TokenResponse(
                                                mint = wallet.tokenAddress,
                                                symbol = token.symbol,
                                                name = token.name,
                                                image = ipfs,
                                            )
                                        }.getOrNull()
                                }

                                val balance = balanceDeferred.await()
                                val tokenInfo = tokenEntityDeferred.await()

                                if (balance != null) {
                                    tokenInfo?.copy(
                                        amount = balance.amount ?: "0",
                                        uiAmountString = balance.uiAmountString,
                                        decimals = balance.decimals
                                    )?.toEntity()
                                } else {
                                    null
                                }
                            }
                        }.getOrNull()
                    }
                }.awaitAll().filterNotNull()
            }
        }


    private suspend fun getTokenBalance(publicKey: String): Result<TokenBalanceResponse> =
        solanaRpcClient.getTokenAccountBalance(publicKey).map {
            it.toResponse()
        }

    private suspend fun getIpfsImage(ipfsUri: String): String {
        val data = ipfsApiService.fetchIpfsData(ipfsUri)
        return data.image
    }

    override suspend fun getTokenQuote(symbol: String): QuoteEntity {
        val response = cmcApiService.getQuotes(symbol, "USD")
        val quote = response.data[symbol]?.quote ?: throw Exception("Quote not found")
        return quote.toEntity()
    }
}