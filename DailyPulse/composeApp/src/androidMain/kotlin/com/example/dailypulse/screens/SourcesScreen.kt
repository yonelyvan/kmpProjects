package com.example.dailypulse.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailypulse.sources.application.Source
import com.example.dailypulse.sources.presentation.SourcesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    sourcesViewModel: SourcesViewModel = getViewModel()
) {
    val sourcesState = sourcesViewModel.sourcesState.collectAsState()

    Column {
        AppBar()
        if (sourcesState.value.error != null)
            ErrorSourceMessage(sourcesState.value.error!!)
        if (sourcesState.value.sources.isNotEmpty())
            SourceListView(sourcesViewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(

) {
    TopAppBar(
        title = { Text(text = "Sources") },
    )
}


@Composable
fun SourceListView(viewModel: SourcesViewModel) {
    SwipeRefresh(
        state = SwipeRefreshState(viewModel.sourcesState.collectAsState().value.isLoading),
        onRefresh = { viewModel.getSources(true) }) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.sourcesState.value.sources.size) { index ->
                SourceItemView(source = viewModel.sourcesState.collectAsState().value.sources[index])
            }
        }
    }
}

@Composable
fun SourceItemView(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.name,
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = source.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.language,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(32.dp))

    }
}


@Composable
fun ErrorSourceMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error
            )
        )
    }
}
