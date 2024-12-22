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

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trycatch.designsystem.icon.AuroraIcons
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.designsystem.theme.LocalColorScheme

@Composable
fun RowScope.AuroraNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
    selectedLabel: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = if (selected) selectedLabel else label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Unspecified,
            unselectedIconColor = Color.Unspecified,
            selectedTextColor = Color.Unspecified,
            unselectedTextColor = Color.Unspecified,
            indicatorColor = Color.Transparent,
        ),
    )
}

@Composable
fun AuroraNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = LocalColorScheme.current.gray24,
        tonalElevation = 0.dp,
        content = content,
    )
}

@Preview
@Composable
private fun AuroraBottomNavigationPreview() {
    AuroraTheme {
        AuroraNavigationBar {
            AuroraNavigationBarItem(
                selected = true,
                onClick = {  },
                icon = {
                    Icon(
                        painter = painterResource(AuroraIcons.Wallet),
                        contentDescription = null,
                        tint = LocalColorScheme.current.gray21
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(AuroraIcons.GradientWallet),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                selectedLabel = { AuroraGradientText("Wallet") }
            )

            AuroraNavigationBarItem(
                selected = false,
                onClick = {  },
                icon = {
                    Icon(
                        painter = painterResource(AuroraIcons.Setting),
                        contentDescription = null,
                        tint = LocalColorScheme.current.gray12
                    )
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(AuroraIcons.GradientSetting),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                label = { AuroraText("Setting", color = LocalColorScheme.current.gray12) },
            )
        }
    }
}