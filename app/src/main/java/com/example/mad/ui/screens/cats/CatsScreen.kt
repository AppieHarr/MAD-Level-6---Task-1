package com.example.mad.ui.screens.cats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.mad.R
import com.example.mad.data.api.Api
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Cat

@Composable
fun CatsScreen(
    viewModel: CatsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cat Image",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp)
                .heightIn(min = 16.dp)
        )

        val catResource: Resource<Cat>? by viewModel.catResource.observeAsState()

        when (catResource) {
            is Resource.Success -> {
                val imageUrl = Api.CATS_BASE_URL + ((catResource as Resource.Success<Cat>).data?.urlExtension ?: "")
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                Text(text = "Cat created at: ${(catResource as Resource.Success<Cat>).data?.creationDate}")
            }
            is Resource.Error -> {
                Text(text = "Error: ${(catResource as Resource.Error<Cat>).message}")
            }
            is Resource.Loading -> {
                Text(text = "Loading...")
            }
            is Resource.Empty -> {
                Text(text = "No cat found")
            }
            else -> {
                Text(text = "Unknown resource")
            }
        }

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.get_pictures)) },
            onClick = { viewModel.getPictures() },
            icon = { Icon(Icons.Filled.Refresh, "") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}
