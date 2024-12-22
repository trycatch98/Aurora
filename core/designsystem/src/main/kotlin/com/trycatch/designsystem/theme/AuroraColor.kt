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

package com.trycatch.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val White  = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val Blue1 = Color(0xFFE2F0FF)
val Blue2 = Color(0xFFC5DFFF)
val Blue3 = Color(0xFFA9CDFF)
val Blue4 = Color(0xFF93BDFF)
val Blue5 = Color(0xFF3D8DFF)
val Blue6 = Color(0xFF517DDB)
val Blue7 = Color(0xFF70A2FF)
val Blue8 = Color(0xFF233E93)

val Green1 = Color(0xFFEEFDE2)
val Green2 = Color(0xFFD8FCC5)
val Green3 = Color(0xFFBCF6A6)
val Green4 = Color(0xFFA0ED8D)
val Green5 = Color(0xFF76E268)
val Green6 = Color(0xFF50C24C)
val Green7 = Color(0xFF34A239)
val Green8 = Color(0xFF21832D)

val Yellow1 = Color(0xFFFFFACD)
val Yellow2 = Color(0xFFFFF39B)
val Yellow3 = Color(0xFFFFEB69)
val Yellow4 = Color(0xFFFFE243)
val Yellow5 = Color(0xFFFFD505)
val Yellow6 = Color(0xFFDBB303)
val Yellow7 = Color(0xFFB79202)
val Yellow8 = Color(0xFF937301)

val Red1 = Color(0xFFFDEDED)
val Red2 = Color(0xFFFBDADC)
val Red3 = Color(0xFFF5A3A7)
val Red4 = Color(0xFFEF6B72)
val Red5 = Color(0xFFEA3943)
val Red6 = Color(0xFFE8212B)
val Red7 = Color(0xFFCB151E)
val Red8 = Color(0xFF941016)

val Turquoise1 = Color(0xFFDAFEE9)
val Turquoise2 = Color(0xFFB5FDDB)
val Turquoise3 = Color(0xFF8FFAD2)
val Turquoise4 = Color(0xFF72F6D1)
val Turquoise5 = Color(0xFF45F0D1)
val Turquoise6 = Color(0xFF32CEC0)
val Turquoise7 = Color(0xFF22ABAC)
val Turquoise8 = Color(0xFF167F8B)

val Primary1 = Color(0xFFFEF7D5)
val Primary2 = Color(0xFFFEEDAD)
val Primary3 = Color(0xFFFEE083)
val Primary4 = Color(0xFFFED365)
val Primary5 = Color(0xFFFEBF32)
val Primary6 = Color(0xFFDA9C24)
val Primary7 = Color(0xFFB67C19)
val Primary8 = Color(0xFF935E0F)

val Gray1 = Color(0xFFF3F5F7)
val Gray2 = Color(0xFFE7EBEF)
val Gray3 = Color(0xFFDAE0E7)
val Gray4 = Color(0xFFCED6DF)
val Gray5 = Color(0xFFC1CBD7)
val Gray6 = Color(0xFFB4C0CF)
val Gray7 = Color(0xFFA8B6C7)
val Gray8 = Color(0xFF9BACBF)
val Gray9 = Color(0xFF8FA2B7)
val Gray10 = Color(0xFF8398AF)
val Gray11 = Color(0xFF768EA7)
val Gray12 = Color(0xFF6A84A0)
val Gray13 = Color(0xFF5F7A95)
val Gray14 = Color(0xFF587089)
val Gray15 = Color(0xFF50657C)
val Gray16 = Color(0xFF485B70)
val Gray17 = Color(0xFF405064)
val Gray18 = Color(0xFF384657)
val Gray19 = Color(0xFF303C4B)
val Gray20 = Color(0xFF28323E)
val Gray21 = Color(0xFF202832)
val Gray22 = Color(0xFF181E25)
val Gray23 = Color(0xFF101419)
val Gray24 = Color(0xFF080A0C)

val TransparentYellow = Color(0x14FFD505)
val TransparentGreen = Color(0x1476E268)
val TransparentBlue = Color(0x145F97FF)
val TransparentRed = Color(0x14EA3943)
val TransparentTurquoise = Color(0x14EA3943)

val Gradient1 = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFF70A2FF),
        0.22f to Color(0xFF72F6D1),
        0.48f to Color(0xFF76E268),
        0.72f to Color(0XFFFFD505),
        1.0f to Color(0XFFF76E64)
    )
)

val Gradient2 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF70A2FF),
        Color(0xFFF76E64)
    )
)

val Gradient3 = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFF70A2FF),
        0.29f to Color(0xFF72E5DA),
        0.51f to Color(0xFF72F6D1),
        1.0f to Color(0XFF76E268)
    )
)

val Gradient4 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFFFFD505),
        Color(0xFFF44336)
    )
)

val Gradient5 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF76E268),
        Color(0xFFFFD505)
    )
)

val Gradient6 = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFF8AD4EC),
        0.22f to Color(0xFFEF96FF),
        0.54f to Color(0xFFFF56A9),
        0.85f to Color(0XFFFFAA6C)
    )
)

val Gradient7 = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF70A2FF),
        Color(0xFF54F0D1)
    )
)

val Gradient8 = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFFA9CDFF),
        0.22f to Color(0xFF72F6D1),
        0.56f to Color(0xFFA0ED8D),
        0.82f to Color(0XFFFED365),
        1.0f to Color(0XFFFAA49E)
    )
)

val Gradient9 = Brush.horizontalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFF607B84),
        0.3f to Color(0xFF39153F),
        0.54f to Color(0xFF510B2E),
        0.85f to Color(0XFF672D03)
    )
)


@Immutable
data class AuroraColors(
    val white: Color = White,
    val black: Color = Black,
    val blue1: Color = Blue1,
    val blue2: Color = Blue2,
    val blue3: Color = Blue3,
    val blue4: Color = Blue4,
    val blue5: Color = Blue5,
    val blue6: Color = Blue6,
    val blue7: Color = Blue7,
    val blue8: Color = Blue8,
    val green1: Color = Green1,
    val green2: Color = Green2,
    val green3: Color = Green3,
    val green4: Color = Green4,
    val green5: Color = Green5,
    val green6: Color = Green6,
    val green7: Color = Green7,
    val green8: Color = Green8,
    val yellow1: Color = Yellow1,
    val yellow2: Color = Yellow2,
    val yellow3: Color = Yellow3,
    val yellow4: Color = Yellow4,
    val yellow5: Color = Yellow5,
    val yellow6: Color = Yellow6,
    val yellow7: Color = Yellow7,
    val yellow8: Color = Yellow8,
    val red1: Color = Red1,
    val red2: Color = Red2,
    val red3: Color = Red3,
    val red4: Color = Red4,
    val red5: Color = Red5,
    val red6: Color = Red6,
    val red7: Color = Red7,
    val red8: Color = Red8,
    val turquoise1: Color = Turquoise1,
    val turquoise2: Color = Turquoise2,
    val turquoise3: Color = Turquoise3,
    val turquoise4: Color = Turquoise4,
    val turquoise5: Color = Turquoise5,
    val turquoise6: Color = Turquoise6,
    val turquoise7: Color = Turquoise7,
    val turquoise8: Color = Turquoise8,
    val primary1: Color = Primary1,
    val primary2: Color = Primary2,
    val primary3: Color = Primary3,
    val primary4: Color = Primary4,
    val primary5: Color = Primary5,
    val primary6: Color = Primary6,
    val primary7: Color = Primary7,
    val primary8: Color = Primary8,
    val gray1: Color = Gray1,
    val gray2: Color = Gray2,
    val gray3: Color = Gray3,
    val gray4: Color = Gray4,
    val gray5: Color = Gray5,
    val gray6: Color = Gray6,
    val gray7: Color = Gray7,
    val gray8: Color = Gray8,
    val gray9: Color = Gray9,
    val gray10: Color = Gray10,
    val gray11: Color = Gray11,
    val gray12: Color = Gray12,
    val gray13: Color = Gray13,
    val gray14: Color = Gray14,
    val gray15: Color = Gray15,
    val gray16: Color = Gray16,
    val gray17: Color = Gray17,
    val gray18: Color = Gray18,
    val gray19: Color = Gray19,
    val gray20: Color = Gray20,
    val gray21: Color = Gray21,
    val gray22: Color = Gray22,
    val gray23: Color = Gray23,
    val gray24: Color = Gray24,
    val transparentYellow: Color = TransparentYellow,
    val transparentGreen: Color = TransparentGreen,
    val transparentBlue: Color = TransparentBlue,
    val transparentRed: Color = TransparentRed,
    val transparentTurquoise: Color = TransparentTurquoise,
    val gradient1: Brush = Gradient1,
    val gradient2: Brush = Gradient2,
    val gradient3: Brush = Gradient3,
    val gradient4: Brush = Gradient4,
    val gradient5: Brush = Gradient5,
    val gradient6: Brush = Gradient6,
    val gradient7: Brush = Gradient7,
    val gradient8: Brush = Gradient8,
    val gradient9: Brush = Gradient9
)

internal val LocalColorScheme = staticCompositionLocalOf { AuroraColors() }