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

package com.trycatch.domain.usecase.wallet

import com.trycatch.domain.WalletGenerator
import com.trycatch.domain.model.Wallet
import com.trycatch.domain.repository.WalletRepository
import com.trycatch.domain.usecase.mnemonic.GetMnemonicUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CreateWalletUseCaseImpl @Inject constructor(
    private val walletGenerator: WalletGenerator,
    private val walletRepository: WalletRepository,
    private val getMnemonicUseCase: GetMnemonicUseCase
): CreateWalletUseCase {

    override suspend fun invoke() {
        val mnemonic = getMnemonicUseCase().first()
        val keyPair = walletGenerator.generateWallet(mnemonic.words)
        walletRepository.setWallet(
            Wallet(
                publicKey = keyPair.publicKey,
                privateKey = keyPair.privateKey
            )
        )
    }
}