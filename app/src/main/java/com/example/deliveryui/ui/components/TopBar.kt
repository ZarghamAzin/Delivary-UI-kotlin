package com.example.deliveryui.ui.components

@androidx.compose.runtime.Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "menu"
                )
            }
        },
        title = {
            Row(
                modifier = Modifier
                    .clickable {},
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.LocationOn,
                    contentDescription = "icon location",
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Pick location", style = MaterialTheme.typography.body1)

            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "icon person"
                )
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}