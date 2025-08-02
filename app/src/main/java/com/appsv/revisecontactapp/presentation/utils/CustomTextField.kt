package com.appsv.revisecontactapp.presentation.utils

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.wear.compose.material.ContentAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value : String,
    onValueChange : (String) -> Unit,
    label : String,
    modifier : Modifier = Modifier,
    singleLine : Boolean = true,
    leadingIcon : ImageVector? = null,
    visualTransformation : VisualTransformation = VisualTransformation.None,
    keyboardOptions : KeyboardOptions = KeyboardOptions.Default
    ) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label)},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
            disabledBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
            disabledLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled),
            leadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.medium),
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = ContentAlpha.disabled),

        ),

        modifier = modifier,
        singleLine = singleLine,
        leadingIcon = leadingIcon?.let {icon ->
            {
                androidx.compose.material3.Icon(imageVector = icon, contentDescription = null)
            }
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions


    )
}