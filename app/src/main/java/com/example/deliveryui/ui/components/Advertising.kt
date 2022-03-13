package com.example.deliveryui.ui.components

import androidx.annotation.DrawableRes
import com.example.deliveryui.ui.theme.Rose


@androidx.compose.runtime.Composable
fun Advertising(
    @DrawableRes backgroundImg: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(24.dp)
            .clip(RoundedCornerShape(10))
    ) {
        Image(
            painter = painterResource(id = backgroundImg),
            contentDescription = "Delivery img",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { onClick() }
                    .height(37.dp)
                    .background(color = Rose, shape = RoundedCornerShape(50))
                    .padding(horizontal = 13.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            )
            { Text(text = "Order now ", fontSize = 10.sp, color = Color.White) }

        }
    }
}