package com.example.deliveryui.ui.components

import com.example.deliveryui.ui.theme.Rose

@androidx.compose.runtime.Composable
fun FloatingSearchButton() {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        backgroundColor = Rose,
        shape = RoundedCornerShape(50),
        modifier = Modifier.size(60.dp),
    ) {
        Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
    }
}