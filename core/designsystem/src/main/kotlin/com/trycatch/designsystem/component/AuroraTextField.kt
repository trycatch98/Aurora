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

package com.trycatch.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.designsystem.theme.LocalColorScheme
import com.trycatch.designsystem.theme.LocalTypography

@Composable
fun AuroraTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    color: Color = LocalColorScheme.current.gray24,
    borderStroke: BorderStroke = BorderStroke(1.dp, LocalColorScheme.current.gray22),
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        textStyle = LocalTypography.current.paragraphSemiBold.copy(color = LocalColorScheme.current.white),
        cursorBrush = SolidColor(LocalColorScheme.current.white),
        decorationBox =
        @Composable { innerTextField ->
            Row(
                modifier = Modifier
                    .background(color = color, shape = shape)
                    .border(
                        border = borderStroke,
                        shape = shape,
                    )
                    .padding(
                        paddingValues = if (isFocused || value.isNotEmpty())
                            PaddingValues(16.dp, 12.dp)
                        else
                            PaddingValues(16.dp, 20.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        modifier =  Modifier
                            .clickable {
                                focusRequester.requestFocus()
                                isFocused = true
                            },
                        text = label,
                        style = if (isFocused || value.isNotEmpty())
                            LocalTypography.current.captionSmallRegular12Line16
                        else
                            LocalTypography.current.paragraphSemiBold,
                        color = LocalColorScheme.current.gray12,
                    )
                    if (isFocused || value.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        innerTextField()
                    }
                }
                Spacer(modifier = Modifier.width(25.dp))
            }
        }
    )
}

@Preview
@Composable
private fun AuroraTextFieldPreview() {
    AuroraTheme {
        AuroraTextField(
            modifier = Modifier.size(327.dp, 64.dp),
            value = "",
            label = "Seed Phrase",
            onValueChange = {}
        )
    }
}

@Preview
@Composable
private fun AuroraTextFieldValuePreview() {
    AuroraTheme {
        AuroraTextField(
            modifier = Modifier.size(327.dp, 64.dp),
            value = "then vacant hub ride avoid girl alien cross usage exist comic upon",
            label = "Seed Phrase",
            onValueChange = {}
        )
    }
}