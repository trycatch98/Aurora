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

import com.solana.vendor.bip39.Mnemonic
import com.solana.vendor.bip39.WordCount
import com.trycatch.crypto.model.toDomain
import com.trycatch.domain.MnemonicGenerator
import javax.inject.Inject

class MnemonicGeneratorImpl @Inject constructor(): MnemonicGenerator {
    override fun generateMnemonic(): com.trycatch.domain.model.Mnemonic {
        return generateMnemonic(WordCount.COUNT_12)
    }

    override fun generateMnemonic(count: Int): com.trycatch.domain.model.Mnemonic {
        val wordCount = when (count) {
            12 -> WordCount.COUNT_12
            15 -> WordCount.COUNT_15
            18 -> WordCount.COUNT_18
            21 -> WordCount.COUNT_21
            24 -> WordCount.COUNT_24
            else -> WordCount.COUNT_12
        }
        return generateMnemonic(wordCount)
    }

    private fun generateMnemonic(wordCount: WordCount): com.trycatch.domain.model.Mnemonic {
        val mnemonic = Mnemonic(wordCount)
        val phrase = mnemonic.phrase
        Mnemonic(phrase = phrase).validate()
        return mnemonic.toDomain()
    }
}