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

package com.trycatch.aurora

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trycatch.aurora.navigation.CryptoWalletNavHost
import com.trycatch.aurora.navigation.TopLevelDestination
import com.trycatch.designsystem.component.AuroraBackground
import com.trycatch.designsystem.component.AuroraGradientText
import com.trycatch.designsystem.component.AuroraNavigationBar
import com.trycatch.designsystem.component.AuroraNavigationBarItem
import com.trycatch.designsystem.component.AuroraText
import com.trycatch.designsystem.theme.AuroraTheme

@Composable
fun CryptoWalletApp() {
    val navController = rememberNavController()

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries
    val currentDestination: NavDestination? = navController
        .currentBackStackEntryAsState().value?.destination

    Scaffold(
        containerColor = AuroraTheme.colorScheme.gray24,
        bottomBar = {
            if (shouldShowBottomNav(topLevelDestinations, currentDestination?.route)) {
                AuroraNavigationBar {
                    topLevelDestinations.forEach {
                        val selected = currentDestination?.route?.hashCode() == it.route.hashCode()
                        AuroraNavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(it.route)
                            },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(it.unselectedIcon),
                                    contentDescription = null,
                                    tint = AuroraTheme.colorScheme.gray12
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    modifier = Modifier.size(30.dp),
                                    painter = painterResource(it.selectedIcon),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                            },
                            label = {
                                AuroraText(
                                    stringResource(it.titleTextId),
                                    style = AuroraTheme.typography.buttonMediumNormal
                                )
                            },
                            selectedLabel = {
                                AuroraGradientText(
                                    stringResource(it.titleTextId),
                                    style = AuroraTheme.typography.buttonMediumNormal
                                )
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            AuroraBackground {
                CryptoWalletNavHost(
                    navController = navController,
                )
            }
        }
    }
}

fun shouldShowBottomNav(destinations: List<TopLevelDestination>, route: Any?) =
    destinations.any {
        route?.hashCode() == it.route.hashCode()
    }