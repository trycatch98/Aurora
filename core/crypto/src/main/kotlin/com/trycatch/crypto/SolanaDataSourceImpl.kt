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

package com.trycatch.crypto

import com.metaplex.lib.modules.token.TokenClient
import com.solana.Solana
import com.solana.actions.getTokenWallets
import com.solana.api.TokenAmountInfoResponse
import com.solana.api.getBalance
import com.solana.api.getTokenAccountBalance
import com.solana.core.PublicKey
import com.trycatch.crypto.model.IpfsResponse
import com.trycatch.crypto.model.TokenResponse
import com.trycatch.data.datasource.SolanaDataSource
import com.trycatch.data.model.TokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import java.math.BigDecimal
import javax.inject.Inject

class SolanaDataSourceImpl @Inject constructor(
    private val json: Json,
    private val okHttpClient: OkHttpClient,
    private val solana: Solana,
    private val tokenClient: TokenClient
): SolanaDataSource {

    companion object {
        private const val LAMPORTS_PER_SOL = 1_000_000_000
        const val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36"
    }

    override suspend fun getBalance(publicKey: String): Result<String> =
        withContext(Dispatchers.IO) {
            solana.api.getBalance(PublicKey(publicKey)).map {
                BigDecimal(it).divide(BigDecimal(LAMPORTS_PER_SOL)).toString()
            }
        }

    override suspend fun getTokens(publicKey: String): Result<List<TokenEntity>> =
        withContext(Dispatchers.IO) {
            solana.action.getTokenWallets(PublicKey(publicKey)).mapCatching { walletList ->
                walletList.map { wallet ->
                    async {
                        runCatching {
                            coroutineScope {
                                val balanceDeferred = async {
                                    getTokenBalance(wallet.pubkey).getOrNull()
                                }
                                val tokenEntityDeferred = async {
                                    tokenClient.findByMint(PublicKey(wallet.token.address))
                                        .map { token ->
                                            val ipfs = fetchIpfsData(token.uri)
                                            TokenResponse(
                                                mint = wallet.token.address,
                                                symbol = token.symbol,
                                                name = token.name,
                                                image = ipfs?.image ?: "",
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


    private suspend fun getTokenBalance(publicKey: String): Result<TokenAmountInfoResponse> {
        return solana.api.getTokenAccountBalance(PublicKey(publicKey))
    }

    private fun fetchIpfsData(ipfs: String): IpfsResponse? {
        val request = Request.Builder().url(ipfs).header("User-Agent", USER_AGENT).build()
        val response = okHttpClient.newCall(request).execute()
        return if (response.isSuccessful) {
            response.body?.string()?.let { responseBody ->
                json.decodeFromString<IpfsResponse>(responseBody)
            }
        } else {
            null
        }
    }
}