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

package com.trycatch.aurora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.trycatch.createwallet.navigation.createWalletScreen
import com.trycatch.home.navigation.HomeRoute
import com.trycatch.home.navigation.homeScreen
import com.trycatch.importwallet.navigation.importWalletScreen
import com.trycatch.onboarding.navigation.OnboardingBaseRoute
import com.trycatch.onboarding.navigation.OnboardingRoute
import com.trycatch.onboarding.navigation.WalletSetupRoute
import com.trycatch.onboarding.navigation.onboardingScreen
import com.trycatch.setting.navigation.settingScreen

@Composable
fun CryptoWalletNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        homeScreen()
        onboardingScreen {
            navController.navigate(WalletSetupRoute) {
                popUpTo(OnboardingRoute) {
                    inclusive = true
                }
            }
        }
        importWalletScreen()
        createWalletScreen()
        settingScreen()
    }
}