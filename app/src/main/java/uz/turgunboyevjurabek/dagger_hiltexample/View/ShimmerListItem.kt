package uz.turgunboyevjurabek.dagger_hiltexample.View

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShimmerListItem(
    isLoading:Boolean,
    contentAfterLoading:@Composable () -> Unit,
    modifier: Modifier
) {

    if (isLoading){
       LazyColumn{
           items(20){
               Column(modifier = Modifier
                   .padding(horizontal = 15.dp )) {
                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.SpaceBetween,
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(vertical = 10.dp)
                   ) {
                       Column(
                           verticalArrangement = Arrangement.SpaceAround,
                           modifier = Modifier
                               .fillMaxHeight()
                       ) {
                           Box(
                               modifier = Modifier
                                   .width(350.dp)
                                   .height(20.dp)
                                   .shimmerEffect()
                           )
                           Spacer(modifier = Modifier.height(20.dp))
                           Box(
                               modifier = Modifier
                                   .width(350.dp)
                                   .height(20.dp)
                                   .shimmerEffect()
                           )
                       }
                       Icon(imageVector = Icons.Default.Star, contentDescription = null, modifier = Modifier)
                   }
                   Divider(color = Color.Black, modifier = Modifier
                       .alpha(0.2f)
                       .padding(horizontal = 10.dp)
                       .shimmerEffect()
                       , thickness = 2.dp

                   )
               }
           }
       }
    }else{
        contentAfterLoading()
    }
    
}

fun Modifier.shimmerEffect():Modifier=composed {
    var  size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transient= rememberInfiniteTransition(label = "")
    val startOffsetX by transient.animateFloat(
        initialValue =-2*size.width.toFloat(),
        targetValue =2*size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(1000)), label = ""
    )
    background(
        brush = Brush.linearGradient(colors = listOf(
            Color(0xFFB8B5B5),
            Color(0xFF8F8B8B),
            Color(0xFFB8B5B5)
        ),
            start = Offset(startOffsetX,0f),
            end = Offset(startOffsetX+size.width.toFloat(),size.height.toFloat())
        )

    ).onGloballyPositioned {
        size=it.size
    }
}