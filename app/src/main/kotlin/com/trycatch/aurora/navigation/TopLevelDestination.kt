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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.trycatch.designsystem.icon.AuroraIcons
import com.trycatch.home.navigation.HomeRoute
import com.trycatch.setting.navigation.SettingRoute
import com.trycatch.aurora.feature.home.R as Home
import com.trycatch.aurora.feature.setting.R as Setting

enum class TopLevelDestination(
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    @StringRes val titleTextId: Int,
    val route: Any,
) {
    HOME(
        selectedIcon = AuroraIcons.GradientWallet,
        unselectedIcon = AuroraIcons.Wallet,
        titleTextId = Home.string.home_title,
        route = HomeRoute
    ),
    SETTING(
        selectedIcon = AuroraIcons.GradientSetting,
        unselectedIcon = AuroraIcons.Setting,
        titleTextId = Setting.string.setting_title,
        route = SettingRoute,
    )
}