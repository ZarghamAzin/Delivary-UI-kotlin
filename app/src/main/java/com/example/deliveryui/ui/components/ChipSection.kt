package com.example.deliveryui.ui.components

import androidx.annotation.DrawableRes
import com.example.deliveryui.ui.theme.Orange

@androidx.compose.runtime.Composable
fun ChipSection(
    chips: List<String>,
    selectedColor: Color = Orange,
    unSelectedColor: Color = Color.LightGray,
    shape: Shape = RoundedCornerShape(70),
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit = {}
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        itemsIndexed(chips) { index, name ->
            val color by animateColorAsState(
                targetValue = if (selectedChipIndex == index) {
                    selectedColor
                } else {
                    unSelectedColor
                },
                animationSpec = tween(durationMillis = 150, easing = LinearEasing)
            )
            Row(
                Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clip(shape)
                    .background(color)
                    .clickable {
                        selectedChipIndex = index
                        onClick()
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "icon",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                }
                Text(text = name, style = MaterialTheme.typography.subtitle1)
            }
        }
    }

}