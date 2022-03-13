package com.example.deliveryui.ui.components

@ExperimentalCoilApi
@androidx.compose.runtime.Composable
fun CoilImage(data: String, size: Dp, loading: Boolean = true) {

    Box(modifier = Modifier.size(size), contentAlignment = Alignment.Center) {

        val painter = rememberImagePainter(
            data = data,
            builder = {}
        )

        val state = painter.state
        Image(
            painter = painter,
            contentDescription = "image food",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (loading && state is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }


    }
}
