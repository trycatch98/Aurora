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

package com.trycatch.createwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trycatch.createwallet.model.toPresentation
import com.trycatch.domain.usecase.mnemonic.CreateMnemonicUseCase
import com.trycatch.domain.usecase.mnemonic.CreateMnemonicValidationUseCase
import com.trycatch.domain.usecase.mnemonic.SetMnemonicUseCase
import com.trycatch.domain.usecase.wallet.CreateWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val createMnemonicUseCase: CreateMnemonicUseCase,
    private val createMnemonicValidationUseCase: CreateMnemonicValidationUseCase,
    private val setMnemonicUseCase: SetMnemonicUseCase,
    private val createWalletUseCase: CreateWalletUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateWalletUiState.DEFAULT)
    val uiState = _uiState

    private val _sideEffects = MutableSharedFlow<CreateWalletSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    private val ignoreSeedPhase = mutableSetOf<String>()

    init {
        fetchMnemonic()
    }

    private fun fetchMnemonic() {
        viewModelScope.launch {
            createMnemonicUseCase()
                .collect { mnemonic ->
                    _uiState.update {
                        it.copy(
                            mnemonic = mnemonic.toPresentation()
                        )
                    }
                }
        }
    }

    fun nextPhase() {
        uiState.value.let { value ->
            when (val phase = Phase.nextPhase(value.phase)) {
                is Phase.ConfirmPhase -> {
                    viewModelScope.launch {
                        createMnemonicValidationUseCase(
                            value.mnemonic.toDomain(),
                            5,
                            ignoreSeedPhase
                        ).collect { mnemonicValidation ->
                            ignoreSeedPhase.add(mnemonicValidation.target)
                            _uiState.update {
                                it.copy(
                                    phase = phase,
                                    mnemonicValidation = mnemonicValidation.toPresentation(),
                                    selectedMnemonic = ""
                                )
                            }
                        }
                    }
                }

                Phase.SuccessPhase -> nextPhase(phase)
                Phase.ViewPhase -> nextPhase(phase)
                null -> {
                    viewModelScope.launch {
                        setMnemonicUseCase(value.mnemonic.toDomain())
                        createWalletUseCase()
                        _sideEffects.emit(CreateWalletSideEffect.NavigateToHome)
                    }
                }
            }
        }
    }

    private fun nextPhase(phase: Phase) {
        _uiState.update {
            it.copy(
                phase = phase
            )
        }
    }

    fun verifyMnemonic() {
        uiState.value.let { value ->
            if (value.mnemonicValidation?.target == value.selectedMnemonic)
                nextPhase()
        }
    }

    fun onMnemonicSelected(seed: String) {
        _uiState.update {
            it.copy(
                selectedMnemonic = seed
            )
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _sideEffects.emit(CreateWalletSideEffect.NavigateToBack)
        }
    }
}