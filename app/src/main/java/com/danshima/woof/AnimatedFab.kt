package com.danshima.woof

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.lerp

const val TRANSITION_DURATION = 300

@Composable
fun AnimatedFab(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isExtended: Boolean = true
) {
    val currentState = if (isExtended) FabState.Extended else FabState.Default
    val transition = updateTransition(targetState = currentState, label = "")
    val opacity by transition.animateFloat(
        transitionSpec = {
                tween(easing = LinearEasing,
                    durationMillis = TRANSITION_DURATION)
        }, label = ""
    ) { state ->
        if (state == FabState.Default) 0f else 1f
    }
    val widthProgress by transition.animateFloat(
        transitionSpec = {
                tween(
                    easing = FastOutSlowInEasing,
                    durationMillis = TRANSITION_DURATION
                )
        }, label = ""
    ) { state ->
        if (state == FabState.Default) 0f else 1f
    }
    ExtendedWithText(
        icon = icon,
        text = text,
        opacityProgress = { opacity },
        widthProgress = { widthProgress },
        modifier = modifier
    )
}

@Composable
private fun ExtendedWithText(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    opacityProgress: () -> Float,
    widthProgress: () -> Float,
    modifier: Modifier
) {
    Layout(
        modifier = modifier,
        content = {
            icon()
            Box(modifier = Modifier.alpha(opacityProgress())) {
                text()
            }
        }
    ) { measurables, constraints ->

        val iconHolder = measurables[0].measure(constraints)
        val textHolder = measurables[1].measure(constraints)
        val height = constraints.maxHeight
        Log.d("Padding", "icon ${iconHolder.width / 2} ${iconHolder.height} text ${textHolder.width} ${textHolder.height} $height")

        val expandedWidth = iconHolder.width + textHolder.width + height

        val width = lerp(height.toDp(), expandedWidth.toDp(), widthProgress())

        layout(width.roundToPx(), height) {
            iconHolder.place(
                iconHolder.width / 2,
                height / 2 - iconHolder.height / 2
            )
            textHolder.place(
                (iconHolder.width * 2),
                height / 2 - textHolder.height / 2
            )
        }
    }
}


enum class FabState {
    Default,
    Extended,
}