package com.trycatch.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.trycatch.designsystem.theme.AuroraTheme
import com.trycatch.designsystem.theme.LocalColorScheme

sealed class IndicatorMode {
    data class Dot(val size: Dp = 8.dp) : IndicatorMode()
    data class Rectangle(
        val width: Dp = 39.dp,
        val height: Dp = 2.dp
    ) : IndicatorMode()
}

@Composable
fun AuroraIndicator(
    current: Int,
    size: Int,
    modifier: Modifier = Modifier,
    indicatorSpace: Dp = 4.dp,
    indicatorMode: IndicatorMode = IndicatorMode.Dot(),
    shape: Shape = CircleShape,
    activeColor: Color = LocalColorScheme.current.blue5,
    inactiveColor: Color = LocalColorScheme.current.gray18,
    highlightOnlyCurrent: Boolean = false,
) {
    require(size >= current + 1) {
        "The size parameter must be greater than or equal to (current + 1). Current: $current, Size: $size"
    }

    val indicatorModifier = when (indicatorMode) {
        is IndicatorMode.Dot -> Modifier.size(indicatorMode.size)
        is IndicatorMode.Rectangle -> Modifier.size(indicatorMode.width, indicatorMode.height)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(indicatorSpace),
    ) {
        repeat(size) { iteration ->
            Box(modifier = indicatorModifier
                .background(
                    color = when {
                        highlightOnlyCurrent && iteration == current -> activeColor
                        !highlightOnlyCurrent && iteration <= current -> activeColor
                        else -> inactiveColor
                    },
                    shape = shape
                )
            )
        }
    }
}

@Composable
fun AuroraLineIndicator(
    current: Int,
    size: Int,
    modifier: Modifier = Modifier,
    width: Dp = 199.dp,
    activeColor: Color = LocalColorScheme.current.blue5,
    inactiveColor: Color = LocalColorScheme.current.gray18,
) {
    require(size >= current + 1) {
        "The size parameter must be greater than or equal to (current + 1). Current: $current, Size: $size"
    }

    Row(
        modifier = modifier.width(width),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) { iteration ->
            Box(modifier = Modifier
                .background(
                    color = if (iteration <= current)
                        activeColor
                    else
                        inactiveColor,
                    shape = CircleShape
                )
                .size(8.dp)
            )

            if (iteration < size - 1) {
                Box(modifier = Modifier
                    .background(
                        color = if (iteration < current)
                            activeColor
                        else
                            inactiveColor,
                        shape = RectangleShape
                    )
                    .height(1.dp)
                    .weight(1f)
                )
            }
        }
    }
}

@Composable
fun AuroraCircleIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    color: Color = LocalColorScheme.current.blue5,
    trackColor: Color = LocalColorScheme.current.gray18,
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        color = color,
        trackColor = trackColor,
    )
}
@Preview
@Composable
private fun AuroraDotIndicatorPreview() {
    AuroraTheme {
        AuroraIndicator(current = 1, size = 3)
    }
}

@Preview
@Composable
private fun AuroraRectangleIndicatorPreview() {
    AuroraTheme {
        AuroraIndicator(
            indicatorMode = IndicatorMode.Rectangle(),
            current = 1,
            size = 3,
            shape = RectangleShape
        )
    }
}

@Preview
@Composable
private fun AuroraCirclePreview() {
    AuroraTheme {
        AuroraCircleIndicator()
    }
}

@Preview
@Composable
private fun AuroraLineIndicatorPreview() {
    AuroraTheme {
        AuroraLineIndicator(current = 1, size = 3)
    }
}