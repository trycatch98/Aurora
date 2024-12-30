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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trycatch.aurora.designsystem.R
import com.trycatch.designsystem.icon.AuroraIcons
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.designsystem.theme.LocalColorScheme
import com.trycatch.designsystem.theme.LocalTypography

@Composable
fun AuroraButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = LocalColorScheme.current.gray21,
    disabledColor: Color = LocalColorScheme.current.gray23,
    textColor: Color = LocalColorScheme.current.white,
    disabledTextColor: Color = LocalColorScheme.current.gray18,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(168.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(
                color = if (enabled)
                    color
                else
                    disabledColor,
                shape = shape
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        contentPadding = contentPadding,
        shape = shape
    ) {
        Text(
            text = text,
            style = LocalTypography.current.buttonLargeNormal,
            color = if (enabled)
                textColor
            else
                disabledTextColor,
        )
    }
}

@Composable
fun AuroraGradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    brush: Brush = LocalColorScheme.current.gradient6,
    disabledBrush: Brush = SolidColor(LocalColorScheme.current.gray23),
    textColor: Color = LocalColorScheme.current.white,
    disabledTextColor: Color = LocalColorScheme.current.gray18,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(168.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(
                brush = if (enabled)
                    brush
                else
                    disabledBrush,
                shape = shape
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
        contentPadding = contentPadding,
        shape = shape
    ) {
        Text(
            text = text,
            style = LocalTypography.current.buttonLargeNormal,
            color = if (enabled)
                textColor
            else
                disabledTextColor
        )
    }
}

@Composable
fun AuroraSeedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = LocalColorScheme.current.gray22,
    disabledColor: Color = LocalColorScheme.current.gray23,
    textColor: Color = LocalColorScheme.current.white,
    disabledTextColor: Color = LocalColorScheme.current.gray18,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled)
                color
            else
                disabledColor,
        ),
        contentPadding = contentPadding,
        shape = shape
    ) {
        Text(
            text = text,
            style = LocalTypography.current.paragraphRegular,
            color = if (enabled)
                textColor
            else
                disabledTextColor
        )
    }
}

@Composable
fun AuroraImageButton(
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = LocalColorScheme.current.gray21,
    disabledColor: Color = LocalColorScheme.current.gray23,
    textColor: Color = LocalColorScheme.current.white,
    disabledTextColor: Color = LocalColorScheme.current.gray18,
    contentPadding: PaddingValues =
        PaddingValues(
            vertical = 5.dp,
            horizontal = 15.dp
        ),
    shape: Shape = RoundedCornerShape(168.dp),
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled)
                color
            else
                disabledColor,
        ),
        contentPadding = contentPadding,
        shape = shape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(icon),
                contentDescription = "back",
            )
            Text(
                text = text,
                style = LocalTypography.current.paragraphRegular,
                color = if (enabled)
                    textColor
                else
                    disabledTextColor
            )
        }
    }
}

@Composable
fun AuroraTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = LocalColorScheme.current.blue5,
    disabledTextColor: Color = LocalColorScheme.current.gray18,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = contentPadding,
    ) {
        Text(
            text = text,
            style = LocalTypography.current.buttonLargeNormal,
            color = if (enabled)
                textColor
            else
                disabledTextColor
        )
    }
}

@Preview
@Composable
private fun AuroraButtonPreview() {
    AuroraTheme {
        AuroraButton(text = "Create a New Wallet", onClick = {})
    }
}


@Preview
@Composable
private fun AuroraGradientButtonPreview() {
    AuroraTheme {
        AuroraGradientButton(text = "Create a New Wallet", onClick = {})
    }
}

@Preview
@Composable
private fun AuroraGradientButtonDisablePreview() {
    AuroraTheme {
        AuroraGradientButton(text = "Create a New Wallet", enabled = false, onClick = {})
    }
}

@Preview
@Composable
private fun AuroraButtonDisablePreview() {
    AuroraTheme {
        AuroraButton(text = "Create a New Wallet", enabled = false, onClick = {})
    }
}

@Preview
@Composable
private fun AuroraSeedButtonPreview() {
    AuroraTheme {
        AuroraSeedButton(text = "future", onClick = {})
    }
}

@Preview
@Composable
private fun AuroraImageButtonPreview() {
    AuroraTheme {
        AuroraImageButton(
            text = "future",
            icon = AuroraIcons.Sent,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun AuroraTextButtonPreview() {
    AuroraTheme {
        AuroraTextButton(text = "Secure", onClick = {})
    }
}