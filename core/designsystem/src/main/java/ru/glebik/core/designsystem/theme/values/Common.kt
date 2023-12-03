package ru.glebik.core.designsystem.theme.values

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Colors(
    val primary: Color,
    val onPrimary: Color,

    val secondary: Color,
    val onSecondary: Color,

    val surface : Color,
    val onSurface : Color,

    val error: Color,
    val onError : Color,

    val tint: Color,
    val background: Color,
)

data class Typography(
    val header: TextStyle,
    val subHeader : TextStyle,
    val body : TextStyle,
    val hint : TextStyle,

    val headerBold: TextStyle,
    val subHeaderBold : TextStyle,
    val bodyBold : TextStyle,
    val hintBold : TextStyle,

    val error: TextStyle,
    val topBar : TextStyle,
)

data class Padding (
    val verticalSmall: Dp,
    val horizontalSmall : Dp,

    val verticalMedium: Dp,
    val horizontalMedium : Dp,

    val verticalBig : Dp,
    val horizontalBig : Dp,

    val verticalLarge: Dp,
    val horizontalLarge : Dp,
)

data class CornerShape(
    val small: CornerBasedShape,
    val medium: CornerBasedShape,
    val large: CornerBasedShape,
)