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

package com.trycatch.domain.di

import com.trycatch.domain.usecase.mnemonic.CreateMnemonicUseCase
import com.trycatch.domain.usecase.mnemonic.CreateMnemonicUseCaseImpl
import com.trycatch.domain.usecase.mnemonic.CreateMnemonicValidationUseCase
import com.trycatch.domain.usecase.mnemonic.CreateMnemonicValidationUseCaseImpl
import com.trycatch.domain.usecase.mnemonic.GetMnemonicUseCase
import com.trycatch.domain.usecase.mnemonic.GetMnemonicUseCaseImpl
import com.trycatch.domain.usecase.mnemonic.SetMnemonicUseCase
import com.trycatch.domain.usecase.mnemonic.SetMnemonicUseCaseImpl
import com.trycatch.domain.usecase.wallet.CreateWalletUseCase
import com.trycatch.domain.usecase.wallet.CreateWalletUseCaseImpl
import com.trycatch.domain.usecase.wallet.GetTokenQuoteUseCase
import com.trycatch.domain.usecase.wallet.GetTokenQuoteUseCaseImpl
import com.trycatch.domain.usecase.wallet.GetTokensUseCase
import com.trycatch.domain.usecase.wallet.GetTokensUseCaseImpl
import com.trycatch.domain.usecase.wallet.GetWalletBalanceUseCase
import com.trycatch.domain.usecase.wallet.GetWalletBalanceUseCaseImpl
import com.trycatch.domain.usecase.wallet.GetWalletUseCase
import com.trycatch.domain.usecase.wallet.GetWalletUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainModule {
    @Binds
    @Singleton
    abstract fun bindCreateMnemonicUseCase(useCase: CreateMnemonicUseCaseImpl): CreateMnemonicUseCase

    @Binds
    @Singleton
    abstract fun bindCreateMnemonicValidationUseCase(useCase: CreateMnemonicValidationUseCaseImpl): CreateMnemonicValidationUseCase

    @Binds
    @Singleton
    abstract fun bindGetMnemonicUseCase(useCase: GetMnemonicUseCaseImpl): GetMnemonicUseCase

    @Binds
    @Singleton
    abstract fun bindSetMnemonicUseCase(useCase: SetMnemonicUseCaseImpl): SetMnemonicUseCase

    @Binds
    @Singleton
    abstract fun bindCreateWalletUseCase(useCase: CreateWalletUseCaseImpl): CreateWalletUseCase

    @Binds
    @Singleton
    abstract fun bindGetWalletUseCase(useCase: GetWalletUseCaseImpl): GetWalletUseCase

    @Binds
    @Singleton
    abstract fun bindGetWalletBalanceUseCase(useCase: GetWalletBalanceUseCaseImpl): GetWalletBalanceUseCase

    @Binds
    @Singleton
    abstract fun bindGetTokensUseCase(useCase: GetTokensUseCaseImpl): GetTokensUseCase

    @Binds
    @Singleton
    abstract fun bindGetTokenQuoteUseCase(useCase: GetTokenQuoteUseCaseImpl): GetTokenQuoteUseCase

}