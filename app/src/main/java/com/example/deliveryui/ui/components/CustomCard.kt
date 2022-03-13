package com.example.deliveryui.ui.components

import com.example.deliveryui.R
import com.example.deliveryui.model.Food
import com.example.deliveryui.ui.theme.Rose

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun CustomCard(
    food: Food,
    backgroundCard: Color = Color.White,
    onClick: () -> Unit,
    cardModifier: Modifier = Modifier.padding(16.dp),
    columnModifier: Modifier = Modifier.padding(vertical = 16.dp, horizontal = 26.dp)
) {

    Card(
        onClick = { onClick() },
        backgroundColor = backgroundCard,
        modifier = cardModifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = columnModifier,
            verticalArrangement = Arrangement.Center
        ) {
            CoilImage(data = food.image, size = 100.dp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = food.name, style = MaterialTheme.typography.body1)
            Row {
                Text(text = food.category, style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.hamburger),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Rose,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append('$')

                    }
                    append(" ${food.price}")
                },
                style = MaterialTheme.typography.body1
            )
        }

    }


}