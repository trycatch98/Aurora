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

package com.trycatch.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trycatch.domain.usecase.wallet.GetTokenQuoteUseCase
import com.trycatch.domain.usecase.wallet.GetTokensUseCase
import com.trycatch.domain.usecase.wallet.GetWalletBalanceUseCase
import com.trycatch.domain.usecase.wallet.GetWalletUseCase
import com.trycatch.home.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getWalletUseCase: GetWalletUseCase,
    private val getWalletBalanceUseCase: GetWalletBalanceUseCase,
    private val getTokensUseCase: GetTokensUseCase,
    private val getTokenQuoteUseCase: GetTokenQuoteUseCase
): ViewModel() {
    private val _sideEffects = MutableSharedFlow<HomeSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    val uiState: StateFlow<HomeUiState> =
        getWalletUseCase()
            .onEach { wallet ->
                if (wallet.publicKey.isEmpty()) {
                    _sideEffects.emit(HomeSideEffect.NavigateToOnboarding)
                }
            }
            .filter { wallet ->
                wallet.publicKey.isNotEmpty()
            }
            .flatMapLatest {
                val balanceFlow =
                    getWalletBalanceUseCase(it.publicKey)
                val tokensFlow =
                    getTokensUseCase(it.publicKey)
                val quoteFlow =
                    getTokenQuoteUseCase("SOL")

                combine(balanceFlow, tokensFlow, quoteFlow) { balanceResult, tokensResult, quoteResult ->
                    val balance = formatDecimal(balanceResult.getOrDefault("0"), 2)
                    val tokens = tokensResult.getOrDefault(emptyList())
                        .map { token ->
                            token.copy(
                                uiAmountString = formatDecimal(token.uiAmountString)
                            ).toPresentation()
                        }.sortedBy { token ->
                            token.name
                        }
                    HomeUiState.Success(
                        balance = balance,
                        price = formatDecimal(
                            BigDecimal(balance)
                                .multiply(
                                    BigDecimal(quoteResult.price)
                                ).toString(),
                            0
                        ),
                        percentChange24h = formatDecimal(
                            quoteResult.percentChange24h.toString(),
                            2)
                        ,
                        sign = if (quoteResult.percentChange24h > 0)
                            Sign.PLUS
                        else
                            Sign.MINUS,
                        tokens
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = HomeUiState.Loading
            )

    private fun formatDecimal(input: String, scale: Int = 4): String {
        val number = input.toBigDecimalOrNull() ?: return input
        val scaledNumber = number.setScale(scale, RoundingMode.DOWN)

        val noDecimal = scaledNumber.stripTrailingZeros().scale() <= 0

        return if (noDecimal) {
            val integerFormat = NumberFormat.getIntegerInstance(Locale.US)
            integerFormat.format(scaledNumber.toBigInteger())
        } else {
            val decimalFormat = NumberFormat.getNumberInstance(Locale.US).apply {
                isGroupingUsed = true
                maximumFractionDigits = scale
            }
            decimalFormat.format(scaledNumber)
        }
    }

    fun onSentClick() {
        TODO()
    }

    fun onReceiveClick() {
        TODO()
    }
}