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

package com.trycatch.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.trycatch.designsystem.component.AuroraGradientText
import com.trycatch.designsystem.component.AuroraIndicator
import com.trycatch.designsystem.component.AuroraText
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.onboarding.model.OnboardingModel

@Composable
internal fun OnboardingRoute(
    navigateToWalletSetup: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is OnboardingSideEffect.NavigateToWalletSetup ->
                        navigateToWalletSetup()
                }
            }
    }
    
    OnboardingScreen(
        onStartClicked = viewModel::clickStart
    )
}

@Composable
internal fun OnboardingScreen(
    onStartClicked: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    val onboardingPageData = remember {
        mutableStateListOf(
            OnboardingModel(
                R.drawable.ic_onboarding1,
                R.string.onboarding1_heading1,
                R.string.onboarding1_heading2
            ),
            OnboardingModel(
                R.drawable.ic_onboarding2,
                R.string.onboarding2_heading1,
                R.string.onboarding2_heading2
            ),
            OnboardingModel(
                R.drawable.ic_onboarding3,
                R.string.onboarding3_heading1,
                R.string.onboarding3_heading2,
                true
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
        ) { page ->
            OnboardingPage(onboardingPageData[page])
        }

        Spacer(modifier = Modifier.weight(1f))

        AuroraIndicator(
            current = pagerState.currentPage,
            size = pagerState.pageCount,
            highlightOnlyCurrent = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuroraButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 24.dp),
            text = stringResource(R.string.onboarding_button),
            onClick = onStartClicked
        )

        Spacer(modifier = Modifier.height(42.dp))
    }
}

@Composable
fun OnboardingPage(onboardingPageData: OnboardingModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(158.dp))

        Image(
            modifier = Modifier
                .size(295.dp),
            painter = painterResource(onboardingPageData.image),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.height(50.dp))

        if (onboardingPageData.isReversed) {
            AuroraGradientText(
                text = stringResource(onboardingPageData.onboardingPrimaryText),
                style = AuroraTheme.typography.onboardingBold
            )

            Spacer(modifier = Modifier.height(14.dp))

            AuroraText(
                text = stringResource(onboardingPageData.onboardingSecondaryText),
                style = AuroraTheme.typography.onboardingNormal
            )
        }
        else {
            AuroraText(
                text = stringResource(onboardingPageData.onboardingPrimaryText),
                style = AuroraTheme.typography.onboardingNormal
            )

            Spacer(modifier = Modifier.height(14.dp))

            AuroraGradientText(
                text = stringResource(onboardingPageData.onboardingSecondaryText),
                style = AuroraTheme.typography.onboardingBold
            )
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    AuroraTheme {
        AuroraBackground {
            OnboardingScreen(onStartClicked = {})
        }
    }
}