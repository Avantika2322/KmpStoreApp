package com.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.presentation.search.viewmodel.SearchUiState
import com.presentation.search.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SearchScreenRoot(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()

    SearchScreenContent(
        modifier = modifier,
        uiState = uiState,
        query = query,
        onQueryChanged = viewModel::updateQuery,
        onClick = onClick
    )
}

@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    uiState: SearchUiState,
    query: String,
    onQueryChanged: (String) -> Unit,
    onClick: (Int) -> Unit
) {
    Scaffold(
        modifier = modifier ,
        topBar = {
            TextField(
                value = query, onValueChange = onQueryChanged,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search Movies") },
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ), maxLines = 1
            )
        }) { innerPadding ->

        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error?.isNotEmpty() == true -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(uiState.error)
                }
            }

            uiState.products.isNotEmpty() -> {
                uiState.products.let { data ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        items(
                            count = data.count(),
                            key = { data[it].id },
                            contentType = { data[it].id }) { index ->
                            val item = data[index]

                            if (item.category.image.isEmpty()) {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp)
                                        .padding(top = 12.dp), contentAlignment = Alignment.Center
                                ) {
                                    Text("No Image Available")
                                }
                            } else {
                                SubcomposeAsyncImage(
                                    model = item.category.image,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(top = 12.dp)
                                        .fillMaxWidth()
                                        .height(400.dp)
                                        .clickable { onClick(item.id) }
                                        .clip(RoundedCornerShape(12.dp)),
                                    loading = {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(400.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            CircularProgressIndicator()
                                        }
                                    }, error = {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(400.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text("Something went wrong.")
                                        }
                                    })

                            }

                        }
                    }
                }

            }

            else -> {
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Data Found")
                }
            }

        }

    }
}