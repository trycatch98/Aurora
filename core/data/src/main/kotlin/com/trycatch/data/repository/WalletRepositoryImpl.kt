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

package com.trycatch.data.repository

import com.trycatch.data.datasource.SolanaDataSource
import com.trycatch.data.datasource.TokenDataSource
import com.trycatch.data.datasource.WalletLocalDataSource
import com.trycatch.data.model.QuoteEntity
import com.trycatch.data.model.toData
import com.trycatch.domain.model.Quote
import com.trycatch.domain.model.Token
import com.trycatch.domain.model.Wallet
import com.trycatch.domain.repository.WalletRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
    private val walletLocalDataSource: WalletLocalDataSource,
    private val solanaDataSource: SolanaDataSource,
    private val tokenDataSource: TokenDataSource
): WalletRepository {
    override fun getWallet(): Flow<Wallet> =
        walletLocalDataSource.getWallet()
            .map { wallet ->
                wallet.toDomain()
            }

    override suspend fun setWallet(wallet: Wallet) =
        walletLocalDataSource.setWallet(wallet.toData())

    override suspend fun getBalance(publicKey: String): Flow<Result<String>> = flow {
        emit(solanaDataSource.getBalance(publicKey))
    }

    override suspend fun getTokens(publicKey: String): Flow<Result<List<Token>>> = flow {
        val tokens = solanaDataSource.getTokens(publicKey).map {
            it.map { token ->
                token.toDomain()
            }
        }
        emit(tokens)
    }

    override suspend fun getTokenQuote(symbol: String): Flow<Quote> = flow {
        val quote = tokenDataSource.getQuote(symbol).toDomain()
        emit(quote)
    }
}