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

package com.trycatch.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.trycatch.designsystem.component.AuroraCircleIndicator
import com.trycatch.designsystem.component.AuroraImageButton
import com.trycatch.designsystem.component.AuroraText
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.home.component.AuroraAsyncImage

@Composable
internal fun HomeRoute(
    navigateToOnboarding: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.NavigateToOnboarding ->
                        navigateToOnboarding()
                }
            }
    }

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState.value,
        viewModel::onSentClick,
        viewModel::onReceiveClick
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onSentClick: () -> Unit,
    onReceiveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            is HomeUiState.Loading -> {
                AuroraCircleIndicator()
            }

            is HomeUiState.Failed -> Unit

            is HomeUiState.Success -> {
                Spacer(modifier = Modifier.height(128.dp))

                AuroraText(
                    text = "${uiState.balance} SOL",
                    style = AuroraTheme.typography.h3
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AuroraText(
                        text = "$${uiState.price}",
                        style = AuroraTheme.typography.h6
                    )
                    AuroraText(
                        text = "${uiState.percentChange24h}%",
                        style = AuroraTheme.typography.h6,
                        color = if (uiState.sign == Sign.PLUS) {
                            AuroraTheme.colorScheme.green5
                        } else {
                            AuroraTheme.colorScheme.red5
                        },
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    AuroraImageButton(
                        text = "Sent",
                        icon = com.trycatch.aurora.designsystem.R.drawable.ic_sent,
                        onClick = onSentClick
                    )
                    AuroraImageButton(
                        text = "Receive",
                        icon = com.trycatch.aurora.designsystem.R.drawable.ic_receive,
                        onClick = onReceiveClick
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement
                        .spacedBy(16.dp)
                ) {
                    items(uiState.tokens) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 16.dp,
                                    horizontal = 32.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AuroraAsyncImage(it.image)
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                AuroraText(text = it.name)
                                AuroraText(
                                    text = "${it.uiAmountString} ${it.symbol}",
                                    fontSize = 14.sp,
                                    color = AuroraTheme.colorScheme.gray6
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}