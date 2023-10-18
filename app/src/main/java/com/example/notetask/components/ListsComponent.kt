package com.example.notetask.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListItemComponent(text: String){
    ListItem(
        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
        colors = ListItemColors(
            containerColor = Color.LightGray,
            headlineColor = Color.Black,
            trailingIconColor = Color.Red,
            disabledHeadlineColor = Color.Cyan,
            disabledLeadingIconColor = Color.DarkGray,
            disabledTrailingIconColor = Color.Green,
            leadingIconColor = Color.Red,
            overlineColor = Color.Yellow,
            supportingTextColor = Color.White
        ),
        headlineContent = { Text(text) },
        leadingContent = {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        },

    )
}