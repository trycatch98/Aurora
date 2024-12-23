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

package com.trycatch.domain.model

class Mnemonic(
    val words: List<String>
) {
    // 니노닉 단어 생성
    fun generateMnemonic(): Mnemonic {
        return Mnemonic(words.shuffled().take(12))
    }

    // `ignoreSeeds`를 제외하고 랜덤으로 하나 선택
    fun pickRandomSeed(ignoreSeeds: Set<String>): String {
        val availableSeeds = words.filterNot { it in ignoreSeeds }
        return availableSeeds.random()
    }

    // 주어진 단어를 제외하고 `selectionCount`만큼 랜덤으로 선택
    fun pickRandomSelection(excludedSeed: String, selectionCount: Int): List<String> {
        val filteredWords = words.filterNot { it == excludedSeed }
        return filteredWords.shuffled().take(selectionCount)
    }
}