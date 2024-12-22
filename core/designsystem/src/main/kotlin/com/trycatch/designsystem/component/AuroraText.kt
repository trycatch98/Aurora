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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.designsystem.theme.LocalColorScheme
import com.trycatch.designsystem.theme.LocalTypography

@Composable
fun AuroraText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    style: TextStyle = LocalTypography.current.title1,
    color: Color = LocalColorScheme.current.white,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.merge(
            color = color,
            fontSize = fontSize,
            textAlign = textAlign ?: TextAlign.Unspecified,
        ),
        textAlign = textAlign
    )
}

@Composable
fun AuroraGradientText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    style: TextStyle = LocalTypography.current.title1,
    brush: Brush = LocalColorScheme.current.gradient6,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        style = style.merge(
            fontSize = fontSize,
            textAlign = textAlign ?: TextAlign.Unspecified,
        ).copy(
            brush = brush
        ),
    )
}

@Composable
fun AuroraSeedText(
    text: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    Box(
        modifier = modifier
            .background(
                color = LocalColorScheme.current.gray22,
                shape = shape
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = LocalTypography.current.paragraphRegular,
            color = LocalColorScheme.current.white,
        )
    }
}

@Preview
@Composable
private fun AuroraTextPreview() {
    AuroraTheme {
        AuroraText(text = "Wallet Setup")
    }
}

@Preview
@Composable
private fun AuroraGradientTextPreview() {
    AuroraTheme {
        AuroraGradientText(text = "Wallet Setup")
    }
}

@Preview
@Composable
private fun AuroraSeedTextPreview() {
    AuroraTheme {
        AuroraSeedText(text = "1. future")
    }
}
