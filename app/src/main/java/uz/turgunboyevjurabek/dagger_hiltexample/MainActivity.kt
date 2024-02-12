package uz.turgunboyevjurabek.dagger_hiltexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.CatsFacts
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.Data
import uz.turgunboyevjurabek.dagger_hiltexample.Model.madels.User
import uz.turgunboyevjurabek.dagger_hiltexample.Model.network.ApiClient
import uz.turgunboyevjurabek.dagger_hiltexample.View.ShimmerListItem
import uz.turgunboyevjurabek.dagger_hiltexample.ViewModel.FactsViewModel
import uz.turgunboyevjurabek.dagger_hiltexample.ui.theme.DaggerhiltExampleTheme
import uz.turgunboyevjurabek.dagger_hiltexample.utils.Status

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerhiltExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context= LocalContext.current
                    var data by remember{
                        mutableStateOf(CatsFacts())
                    }
                    val list = ArrayList<CatsFacts>()
                    val scope = rememberCoroutineScope()
                    val viewModel= viewModels<FactsViewModel>()
                    var isLoading by remember {
                        mutableStateOf(true)
                    }

                    LaunchedEffect(key1 = true){
                        viewModel.value.getFactsFromApi().observe(this@MainActivity, Observer { it ->
                            when(it.status){
                                Status.LOADING -> {
                                    Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                                }
                                Status.ERROR -> {
                                    Toast.makeText(context,  "afsus ${it.message}", Toast.LENGTH_SHORT).show()
                                }
                                Status.SUCCESS -> {
                                    data = it.data!!
                                    isLoading=false

                                }
                            }
                        })
                    }
                    ShimmerListItem(
                        isLoading = isLoading,
                        contentAfterLoading = {
                            list.addAll(listOf(data))
                            LazyColumn(modifier = Modifier
                                .fillMaxSize()
                            ){
                                data.data?.let {it->
                                    items(it.size){
                                        UI(list,it)
                                    }
                                }
                            }
                        },
                        modifier =Modifier
                            .padding(16.dp)

                    )


                }
            }
        }
    }
}

@Composable
fun UI(list:ArrayList<CatsFacts>,it:Int) {
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
                Text(
                    text = list[0].data?.get(it)?.fact.toString(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black,
                    modifier = Modifier
                        .width(350.dp)

                )
            }
                Icon(imageVector = Icons.Default.Star, contentDescription = null, modifier = Modifier)
        }
        Divider(color = Color.Black, modifier = Modifier
            .alpha(0.2f)
            .padding(horizontal = 10.dp), thickness = 2.dp
        )
    }

}