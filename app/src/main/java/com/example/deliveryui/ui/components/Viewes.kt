package com.example.deliveryui.ui.components

import com.example.deliveryui.ui.theme.Orange

@androidx.compose.runtime.Composable
fun ViewAll() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(text = "Popular Now", style = MaterialTheme.typography.body1)

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = "View all",
                style = MaterialTheme.typography.subtitle1.copy(color = Orange)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Icon(
                imageVector = Icons.Rounded.ArrowDropDown,
                contentDescription = "Arrow drop down",
                tint = Color.White,
                modifier = Modifier
                    .background(color = Orange, CircleShape)
                    .size(20.dp)
            )
        }
    }
}