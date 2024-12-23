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

package com.trycatch.createwallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.trycatch.aurora.feature.createwallet.R
import com.trycatch.createwallet.model.MnemonicModel
import com.trycatch.createwallet.model.MnemonicValidationModel
import com.trycatch.designsystem.component.AuroraAppbar
import com.trycatch.designsystem.component.AuroraBackground
import com.trycatch.designsystem.component.AuroraGradientButton
import com.trycatch.designsystem.component.AuroraGradientText
import com.trycatch.designsystem.component.AuroraIndicator
import com.trycatch.designsystem.component.AuroraLineIndicator
import com.trycatch.designsystem.component.AuroraSeedButton
import com.trycatch.designsystem.component.AuroraSeedText
import com.trycatch.designsystem.component.AuroraText
import com.trycatch.designsystem.component.IndicatorMode
import com.trycatch.designsystem.theme.AuroraTheme

@Composable
internal fun CreateWalletRoute(
    navigateToHome: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: CreateWalletViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is CreateWalletSideEffect.NavigateToHome ->
                        navigateToHome()
                    is CreateWalletSideEffect.NavigateToBack ->
                        navigateToBack()
                }
            }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CreateWalletScreen(
        uiState = uiState,
        onBackClicked = viewModel::onBackClicked,
        onMnemonicSelected = viewModel::onMnemonicSelected,
        onNextClicked = viewModel::nextPhase,
        onVerifyClicked = viewModel::verifyMnemonic,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateWalletScreen(
    uiState: CreateWalletUiState,
    onBackClicked: () -> Unit,
    onMnemonicSelected: (String) -> Unit,
    onNextClicked: () -> Unit,
    onVerifyClicked: () -> Unit
) {
    val phase = uiState.phase
    val currentPhase = when (phase) {
        is Phase.ViewPhase -> 0
        is Phase.ConfirmPhase -> 1
        is Phase.SuccessPhase -> 2
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuroraAppbar(
            onBackClicked = onBackClicked
        ) {
            AuroraLineIndicator(
                current = currentPhase,
                size = uiState.phaseCount
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (phase) {
            is Phase.ViewPhase -> ViewMnemonic(
                uiState.mnemonic,
                onNextClicked = onNextClicked
            )
            is Phase.ConfirmPhase -> ConfirmMnemonic(
                step = phase.step,
                totalSteps = Phase.ConfirmPhase.TOTAL_STEPS,
                selectedMnemonic = uiState.selectedMnemonic,
                mnemonicValidation = uiState.mnemonicValidation,
                onMnemonicSelected = {
                    onMnemonicSelected(it)
                },
                onNextClicked = onVerifyClicked
            )
            is Phase.SuccessPhase -> SuccessScreen(onCompleted = onNextClicked)
        }
    }
}

@Composable
internal fun ViewMnemonic(
    mnemonic: MnemonicModel,
    onNextClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuroraGradientText(
            text = stringResource(R.string.heading_view_seed),
            style = AuroraTheme.typography.title1,
            brush = AuroraTheme.colorScheme.gradient7
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuroraText(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.description_view_seed),
            style = AuroraTheme.typography.paragraphRegular,
            color = AuroraTheme.colorScheme.gray9,
        )

        Spacer(modifier = Modifier.height(64.dp))

        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .border(
                    width = 1.dp,
                    color = AuroraTheme.colorScheme.gray22,
                    shape = RoundedCornerShape(8.dp)
                ),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            itemsIndexed(mnemonic.words) { index, seed ->
                AuroraSeedText("${index + 1}. $seed")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AuroraGradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.button_next),
            onClick = onNextClicked
        )

        Spacer(modifier = Modifier.height(42.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ConfirmMnemonic(
    step: Int,
    totalSteps: Int,
    selectedMnemonic: String,
    mnemonicValidation: MnemonicValidationModel?,
    onMnemonicSelected: (String) -> Unit = {},
    onNextClicked: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AuroraGradientText(
            text = stringResource(R.string.heading_confirm_seed),
            style = AuroraTheme.typography.title1,
            brush = AuroraTheme.colorScheme.gradient7
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuroraText(
            modifier = Modifier
                .padding(horizontal = 72.dp),
            text = stringResource(R.string.description_confirm_seed),
            style = AuroraTheme.typography.paragraphRegular,
            color = AuroraTheme.colorScheme.white,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp))

        mnemonicValidation?.apply {
            AuroraGradientText(
                text = "${targetIndex + 1}. $selectedMnemonic",
                style = AuroraTheme.typography.bigType,
                brush = if (selectedMnemonic.isNotEmpty())
                    AuroraTheme.colorScheme.gradient7
                else
                    SolidColor(AuroraTheme.colorScheme.gray12)
            )

            Spacer(modifier = Modifier.height(102.dp))

            AuroraIndicator(
                modifier = Modifier.width(133.99.dp),
                indicatorMode = IndicatorMode.Rectangle(
                    width = 39.33.dp,
                    height = 2.dp
                ),
                current = step,
                size = totalSteps,
                shape = RectangleShape
            )

            Spacer(modifier = Modifier.height(37.5.dp))

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
                    .padding(horizontal = 24.dp)
                    .border(
                        width = 1.dp,
                        color = AuroraTheme.colorScheme.gray22,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxItemsInEachRow = 3,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalArrangement = Arrangement.spacedBy(
                    16.dp,
                    Alignment.CenterHorizontally
                )
            ) {
                repeat(words.size) {
                    AuroraSeedButton(
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(40.dp),
                        text = words[it],
                        onClick = {
                            onMnemonicSelected(words[it])
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        AuroraGradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.button_next),
            enabled = selectedMnemonic.isNotEmpty(),
            onClick = onNextClicked
        )

        Spacer(modifier = Modifier.height(42.dp))
    }
}

@Composable
internal fun SuccessScreen(
    onCompleted: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(295.dp),
            painter = painterResource(R.drawable.ic_success),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.height(24.dp))

        AuroraGradientText(
            text = stringResource(R.string.heading_success),
            style = AuroraTheme.typography.bigType,
            brush = AuroraTheme.colorScheme.gradient7
        )

        Spacer(modifier = Modifier.height(24.dp))

        AuroraText(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.description_success1),
            style = AuroraTheme.typography.paragraphRegular,
            color = AuroraTheme.colorScheme.white,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        AuroraText(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.description_success2),
            style = AuroraTheme.typography.paragraphRegular,
            color = AuroraTheme.colorScheme.white,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        AuroraGradientButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.button_next),
            onClick = onCompleted
        )

        Spacer(modifier = Modifier.height(42.dp))
    }
}

@Preview
@Composable
internal fun ViewSeedScreenPreview() {
    AuroraTheme {
        AuroraBackground {
            ViewMnemonic(MnemonicModel(List(12) { "" }))
        }
    }
}

@Preview
@Composable
internal fun ConfirmMnemonicScreenPreview() {
    AuroraTheme {
        AuroraBackground {
            ConfirmMnemonic(
                0,
                3,
                "",
                MnemonicValidationModel(
                    target = "frequent",
                    targetIndex = 1,
                    words = listOf(
                        "future", " frequent", "target", "abuse", "organ", "bubble"
                    )
                ),
            )
        }
    }
}

@Preview
@Composable
internal fun SuccessScreenPreview() {
    AuroraTheme {
        AuroraBackground {
            SuccessScreen()
        }
    }
}