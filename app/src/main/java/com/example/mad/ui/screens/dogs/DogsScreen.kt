package com.example.mad.ui.screens.dogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mad.R
import com.example.mad.data.api.Api
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Dog

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val dogResource: Resource<Dog> by viewModel.dogResource.observeAsState(initial = Resource.Empty())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dog Image",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .heightIn(min = 16.dp)
        )

        when (dogResource) {
            is Resource.Success -> {
                val imageUrl =  Api.dogsClient.toString() + "api/img" + ((dogResource as Resource.Success<Dog>).data?.urlExtension ?: "")
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Text(text = "Dog created at: ${(dogResource as Resource.Success<Dog>).data?.creationDate}")
            }
            is Resource.Error -> {
                Text(text = "Error: ${(dogResource as Resource.Error<Dog>).message}")
            }
            is Resource.Loading -> {
                Text(text = "Loading...")
            }
            is Resource.Empty -> {
                Text(text = "No dog found")
            }
            else -> {
                Text(text = "Unknown resource")
            }
        }

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.get_pictureDog)) },
            onClick = { viewModel.getPictureDog() },
            icon = { Icon(Icons.Filled.Refresh, "") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}