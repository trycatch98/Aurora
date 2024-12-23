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

package com.trycatch.local.datasource

import androidx.datastore.core.DataStore
import com.trycatch.aurora.core.local.UserPreferences
import com.trycatch.aurora.core.local.copy
import com.trycatch.data.datasource.MnemonicDataSource
import com.trycatch.data.model.MnemonicEntity
import com.trycatch.local.model.toData
import com.trycatch.local.model.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MnemonicDataSourceImpl @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
): MnemonicDataSource {
    override fun getMnemonic(): Flow<MnemonicEntity> =
        userPreferences.data
            .map { preferences ->
                preferences.mnemonic.toData()
            }

    override suspend fun setMnemonic(mnemonic: MnemonicEntity) {
        userPreferences.updateData { preferences ->
            preferences.copy {
                this.mnemonic = mnemonic.toLocal()
            }
        }
    }
}