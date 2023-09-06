package com.android.yambasama.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.rakutentest.android.ui.theme.Blue10
import com.rakutentest.android.ui.theme.Blue40
import com.rakutentest.android.ui.theme.Blue80
import com.rakutentest.android.ui.theme.Blue90
import com.rakutentest.android.ui.theme.BlueGrey30
import com.rakutentest.android.ui.theme.BlueGrey50
import com.rakutentest.android.ui.theme.BlueGrey60
import com.rakutentest.android.ui.theme.BlueGrey80
import com.rakutentest.android.ui.theme.BlueGrey90
import com.rakutentest.android.ui.theme.DarkBlue10
import com.rakutentest.android.ui.theme.DarkBlue40
import com.rakutentest.android.ui.theme.DarkBlue90
import com.rakutentest.android.ui.theme.Grey10
import com.rakutentest.android.ui.theme.Grey20
import com.rakutentest.android.ui.theme.Grey80
import com.rakutentest.android.ui.theme.Grey90
import com.rakutentest.android.ui.theme.Grey95
import com.rakutentest.android.ui.theme.Grey99
import com.rakutentest.android.ui.theme.RakutenTypography
import com.rakutentest.android.ui.theme.Red10
import com.rakutentest.android.ui.theme.Red20
import com.rakutentest.android.ui.theme.Red30
import com.rakutentest.android.ui.theme.Red40
import com.rakutentest.android.ui.theme.Red80
import com.rakutentest.android.ui.theme.Red90
import com.rakutentest.android.ui.theme.Yellow10
import com.rakutentest.android.ui.theme.Yellow30
import com.rakutentest.android.ui.theme.Yellow40
import com.rakutentest.android.ui.theme.Yellow90


private val RakutenDarkColorScheme = darkColorScheme(
    primary = Blue40,
    onPrimary = Blue40,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    secondary = DarkBlue40,
    onSecondary = Color.White,
    secondaryContainer = DarkBlue90,
    onSecondaryContainer = DarkBlue10,
    tertiary = Yellow40,
    onTertiary = Color.White,
    tertiaryContainer = Yellow30,
    onTertiaryContainer = Yellow90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = Grey10,
    onSurface = Grey80,
    inverseSurface = Grey90,
    inverseOnSurface = Grey20,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey60
)

private val RakutenLightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = Color.White,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    secondary = DarkBlue40,
    onSecondary = Color.White,
    secondaryContainer = DarkBlue90,
    onSecondaryContainer = DarkBlue10,
    tertiary = Yellow40,
    onTertiary = Color.White,
    tertiaryContainer = Yellow90,
    onTertiaryContainer = Yellow10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey99,
    onSurface = Grey10,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50
)

@SuppressLint("NewApi")
@Composable
fun RakutenTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val myColorScheme = when {
        dynamicColor && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        dynamicColor && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        isDarkTheme -> RakutenDarkColorScheme
        else -> RakutenLightColorScheme
    }

    MaterialTheme(
        colorScheme = myColorScheme,
        typography = RakutenTypography,
    ) {
        // TODO (M3): MaterialTheme doesn't provide LocalIndication, remove when it does
        val rippleIndication = rememberRipple()
        CompositionLocalProvider(
            LocalIndication provides rippleIndication,
            content = content
        )
    }
}