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

import com.trycatch.createwallet.model.MnemonicModel
import com.trycatch.createwallet.model.MnemonicValidationModel

data class CreateWalletUiState(
    val mnemonic: MnemonicModel,
    val phase: Phase,
    val phaseCount: Int,
    val mnemonicValidation: MnemonicValidationModel?,
    val selectedMnemonic: String,
) {
    companion object {
        val DEFAULT = CreateWalletUiState(
            mnemonic = MnemonicModel(emptyList()),
            phase = Phase.ViewPhase,
            phaseCount = Phase.TOTAL_PHASE,
            mnemonicValidation = null,
            selectedMnemonic = "",
        )
    }
}

sealed class Phase {
    data object ViewPhase : Phase()

    data class ConfirmPhase(val step: Int) : Phase() {
        companion object {
            const val TOTAL_STEPS = 3
        }

        fun nextStep(): ConfirmPhase? {
            return if (step + 1 < TOTAL_STEPS) ConfirmPhase(step + 1) else null
        }
    }

    data object SuccessPhase : Phase()

    companion object {
        const val TOTAL_PHASE = 3

        fun nextPhase(current: Phase): Phase? {
            return when (current) {
                is ViewPhase -> ConfirmPhase(step = 0)
                is ConfirmPhase -> current.nextStep() ?: SuccessPhase
                is SuccessPhase -> null
            }
        }
    }
}