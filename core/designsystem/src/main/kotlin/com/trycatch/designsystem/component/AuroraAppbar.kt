package com.trycatch.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.trycatch.aurora.designsystem.R
import com.trycatch.designsystem.icon.AuroraIcons
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.designsystem.theme.LocalColorScheme
import com.trycatch.designsystem.theme.LocalTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuroraAppbar(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults
        .centerAlignedTopAppBarColors()
        .copy(
            containerColor = LocalColorScheme.current.black,
            titleContentColor = LocalColorScheme.current.white,
            navigationIconContentColor = LocalColorScheme.current.white,
        ),
    content: @Composable () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Box(
                modifier = Modifier
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(
                    painter = painterResource(AuroraIcons.ArrowBack),
                    contentDescription = "back",
                    tint = LocalColorScheme.current.white,
                )
            }
        },
        colors = colors,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AuroraBackAppbarTextPreview() {
    AuroraTheme {
        AuroraAppbar(
            onBackClicked = {}
        ) {
            Text(
                text = "Import From Seed",
                style = LocalTypography.current.title2,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun AuroraBackAppbarIndicatorPreview() {
    AuroraTheme {
        AuroraAppbar(
            onBackClicked = {}
        ) {
            AuroraLineIndicator(
                current = 1,
                size = 3
            )
        }
    }
}