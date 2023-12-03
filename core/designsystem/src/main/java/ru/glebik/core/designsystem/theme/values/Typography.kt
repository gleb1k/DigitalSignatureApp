package ru.glebik.core.designsystem.theme.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val baseLightTypography = Typography(
    header = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 32.sp,
        fontFamily = FontFamily.Default
    ),
    subHeader = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 28.sp,
        fontFamily = FontFamily.Default
    ),
    body = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    ),
    hint = TextStyle(
        color = baseLightPalette.tint,
        fontSize = 14.sp,
        fontFamily = FontFamily.Default
    ),
    //----------------------------
    headerBold = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 32.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    subHeaderBold = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 28.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    bodyBold = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    hintBold = TextStyle(
        color = baseLightPalette.tint,
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),

    error = TextStyle(
        color = baseLightPalette.onError,
        fontSize = 18.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    topBar = TextStyle(
        color = baseLightPalette.onSurface,
        fontSize = 22.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
)

//region example

//internal object TypeScaleTokens {
//    val BodyLargeFont = TypefaceTokens.Plain
//    val BodyLargeLineHeight = 24.0.sp
//    val BodyLargeSize = 16.sp
//    val BodyLargeTracking = 0.5.sp
//    val BodyLargeWeight = TypefaceTokens.WeightRegular
//    val BodyMediumFont = TypefaceTokens.Plain
//    val BodyMediumLineHeight = 20.0.sp
//    val BodyMediumSize = 14.sp
//    val BodyMediumTracking = 0.2.sp
//    val BodyMediumWeight = TypefaceTokens.WeightRegular
//    val BodySmallFont = TypefaceTokens.Plain
//    val BodySmallLineHeight = 16.0.sp
//    val BodySmallSize = 12.sp
//    val BodySmallTracking = 0.4.sp
//    val BodySmallWeight = TypefaceTokens.WeightRegular
//    val DisplayLargeFont = TypefaceTokens.Brand
//    val DisplayLargeLineHeight = 64.0.sp
//    val DisplayLargeSize = 57.sp
//    val DisplayLargeTracking = -0.2.sp
//    val DisplayLargeWeight = TypefaceTokens.WeightRegular
//    val DisplayMediumFont = TypefaceTokens.Brand
//    val DisplayMediumLineHeight = 52.0.sp
//    val DisplayMediumSize = 45.sp
//    val DisplayMediumTracking = 0.0.sp
//    val DisplayMediumWeight = TypefaceTokens.WeightRegular
//    val DisplaySmallFont = TypefaceTokens.Brand
//    val DisplaySmallLineHeight = 44.0.sp
//    val DisplaySmallSize = 36.sp
//    val DisplaySmallTracking = 0.0.sp
//    val DisplaySmallWeight = TypefaceTokens.WeightRegular
//    val HeadlineLargeFont = TypefaceTokens.Brand
//    val HeadlineLargeLineHeight = 40.0.sp
//    val HeadlineLargeSize = 32.sp
//    val HeadlineLargeTracking = 0.0.sp
//    val HeadlineLargeWeight = TypefaceTokens.WeightRegular
//    val HeadlineMediumFont = TypefaceTokens.Brand
//    val HeadlineMediumLineHeight = 36.0.sp
//    val HeadlineMediumSize = 28.sp
//    val HeadlineMediumTracking = 0.0.sp
//    val HeadlineMediumWeight = TypefaceTokens.WeightRegular
//    val HeadlineSmallFont = TypefaceTokens.Brand
//    val HeadlineSmallLineHeight = 32.0.sp
//    val HeadlineSmallSize = 24.sp
//    val HeadlineSmallTracking = 0.0.sp
//    val HeadlineSmallWeight = TypefaceTokens.WeightRegular
//    val LabelLargeFont = TypefaceTokens.Plain
//    val LabelLargeLineHeight = 20.0.sp
//    val LabelLargeSize = 14.sp
//    val LabelLargeTracking = 0.1.sp
//    val LabelLargeWeight = TypefaceTokens.WeightMedium
//    val LabelMediumFont = TypefaceTokens.Plain
//    val LabelMediumLineHeight = 16.0.sp
//    val LabelMediumSize = 12.sp
//    val LabelMediumTracking = 0.5.sp
//    val LabelMediumWeight = TypefaceTokens.WeightMedium
//    val LabelSmallFont = TypefaceTokens.Plain
//    val LabelSmallLineHeight = 16.0.sp
//    val LabelSmallSize = 11.sp
//    val LabelSmallTracking = 0.5.sp
//    val LabelSmallWeight = TypefaceTokens.WeightMedium
//    val TitleLargeFont = TypefaceTokens.Brand
//    val TitleLargeLineHeight = 28.0.sp
//    val TitleLargeSize = 22.sp
//    val TitleLargeTracking = 0.0.sp
//    val TitleLargeWeight = TypefaceTokens.WeightRegular
//    val TitleMediumFont = TypefaceTokens.Plain
//    val TitleMediumLineHeight = 24.0.sp
//    val TitleMediumSize = 16.sp
//    val TitleMediumTracking = 0.2.sp
//    val TitleMediumWeight = TypefaceTokens.WeightMedium
//    val TitleSmallFont = TypefaceTokens.Plain
//    val TitleSmallLineHeight = 20.0.sp
//    val TitleSmallSize = 14.sp
//    val TitleSmallTracking = 0.1.sp
//    val TitleSmallWeight = TypefaceTokens.WeightMedium
//}

//endregion

internal val baseDarkTypography = Typography(
    header = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 28.sp,
        fontFamily = FontFamily.Default
    ),
    subHeader = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 24.sp,
        fontFamily = FontFamily.Default
    ),
    body = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default
    ),
    hint = TextStyle(
        color = baseDarkPalette.tint,
        fontSize = 14.sp,
        fontFamily = FontFamily.Default
    ),
    //----------------------------
    headerBold = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 28.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    subHeaderBold = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 24.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    bodyBold = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 16.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    hintBold = TextStyle(
        color = baseDarkPalette.tint,
        fontSize = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),

    error = TextStyle(
        color = baseDarkPalette.onError,
        fontSize = 18.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
    topBar = TextStyle(
        color = baseDarkPalette.onSurface,
        fontSize = 20.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold
    ),
)