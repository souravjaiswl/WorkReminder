package com.example.workreminder


import android.os.Build
import android.os.Bundle
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.size.OriginalSize
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.workreminder.navigation.SetupNavGraph
import com.example.workreminder.toodo.Setter
import com.example.workreminder.ui.theme.WorkReminderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkReminderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun GifImageExample(
    data: Int,
    backgroundColor: Color,
    contentAlignment: Alignment,
    shape: Shape = RectangleShape,
    contentScale: ContentScale = ContentScale.Fit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = contentAlignment // Use the provided contentAlignment
    ) {
        val context = LocalContext.current
        val imageLoader = coil.ImageLoader.Builder(context)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context))
                } else {
                    add(GifDecoder())
                }
            }
            .build()
        Image(
            painter = rememberImagePainter(
                imageLoader = imageLoader,
                data = data,
                builder = {
                    size(OriginalSize)
                }
            ),
            contentDescription = null,
            contentScale = contentScale,
            modifier = Modifier
                .size(360.dp)
                .clip(shape = shape)
                .background(backgroundColor),
        )
    }
}