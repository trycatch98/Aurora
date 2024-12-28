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
import com.solana.api.getBalance
import com.solana.api.getTokenAccountBalance
import com.solana.core.PublicKey
import com.trycatch.crypto.model.TokenBalanceCrypto
import com.trycatch.crypto.model.TokenCrypto
import com.trycatch.crypto.model.TokenWalletCrypto
import com.trycatch.crypto.model.toCrypto
import javax.inject.Inject

internal class SolanaRpcClient @Inject constructor(
    private val solana: Solana,
    private val tokenClient: TokenClient
): RpcClient {
    override suspend fun getBalance(publicKey: String): Result<Long> =
        solana.api.getBalance(PublicKey(publicKey))

    override suspend fun getTokenWallets(publicKey: String): Result<List<TokenWalletCrypto>> =
        solana.action.getTokenWallets(PublicKey(publicKey)).map {
            it.map { wallet ->
                wallet.toCrypto()
            }
        }

    override suspend fun getTokenAccountBalance(publicKey: String): Result<TokenBalanceCrypto> =
        solana.api.getTokenAccountBalance(PublicKey(publicKey)).map {
            it.toCrypto()
        }

    override suspend fun findByMint(publicKey: String): Result<TokenCrypto> =
        tokenClient.findByMint(PublicKey(publicKey))
            .map { token ->
                TokenCrypto(
                    mint = publicKey,
                    symbol = token.symbol,
                    name = token.name,
                    uri = token.uri,
                )
            }
}