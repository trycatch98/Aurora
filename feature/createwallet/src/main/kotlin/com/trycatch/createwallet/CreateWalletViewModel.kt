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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CreateWalletUiState.DEFAULT)
    val uiState = _uiState

    private val _sideEffects = MutableSharedFlow<CreateWalletSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    fun nextPhase() {
    }

    private fun nextPhase(phase: Phase) {
    }

    fun verifyMnemonic() {
    }

    fun onMnemonicSelected(seed: String) {
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _sideEffects.emit(CreateWalletSideEffect.NavigateToBack)
        }
    }
}