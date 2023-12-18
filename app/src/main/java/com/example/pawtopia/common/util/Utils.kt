package com.example.pawtopia.common.util

import android.text.TextUtils
import android.util.Patterns
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import com.example.pawtopia.common.state.InputTextState

// Validating Email
fun String.isValidEmail(): Boolean =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

// Divider full Width
fun Modifier.fillWidthOfParent(parentPadding: Dp) = this.then(
    layout { measurable, constraints ->
        // This is to force layout to go beyond the borders of its parent
        val placeable = measurable.measure(
            constraints.copy(
                maxWidth = constraints.maxWidth + 2 * parentPadding.roundToPx(),
            ),
        )
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    },
)

fun InputTextState.validate(): Boolean =
    this.value.isEmpty() || this.isError