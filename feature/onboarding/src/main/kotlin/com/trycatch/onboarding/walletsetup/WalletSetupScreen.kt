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

package com.trycatch.onboarding.walletsetup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.trycatch.aurora.feature.onboarding.R
import com.trycatch.designsystem.component.AuroraBackground
import com.trycatch.designsystem.component.AuroraButton
import com.trycatch.designsystem.component.AuroraGradientButton
import com.trycatch.designsystem.component.AuroraText
import com.trycatch.designsystem.theme.AuroraTheme

@Composable
internal fun WalletSetupRoute(
    navigateToImportWallet: () -> Unit,
    navigateToCreateWallet: () -> Unit,
    viewModel: WalletSetupViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is WalletSetupSideEffect.NavigateToImportWallet ->
                        navigateToImportWallet()
                    is WalletSetupSideEffect.NavigateToCreateWallet ->
                        navigateToCreateWallet()
                }
            }
    }

    WalletSetupScreen(
        onImportWalletClick = viewModel::clickImportWallet,
        onCreateWalletClick = viewModel::clickCreateWallet
    )
}

@Composable
internal fun WalletSetupScreen(
    onImportWalletClick: () -> Unit,
    onCreateWalletClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(158.dp))

        Image(
            modifier = Modifier
                .size(295.dp),
            painter = painterResource(R.drawable.ic_wallet_set_up),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.height(50.dp))

        AuroraText(
            text = stringResource(R.string.setup_heading),
            style = AuroraTheme.typography.bigType
        )

        Spacer(modifier = Modifier.weight(1f))

        AuroraButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.setup_import_button),
            onClick = onImportWalletClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuroraGradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.setup_create_button),
            onClick = onCreateWalletClick
        )

        Spacer(modifier = Modifier.height(42.dp))
    }
}

@Preview
@Composable
fun WalletSetUpScreenPreview() {
    AuroraTheme {
        AuroraBackground {
            WalletSetupScreen(
                onImportWalletClick = {},
                onCreateWalletClick = {}
            )
        }
    }
}