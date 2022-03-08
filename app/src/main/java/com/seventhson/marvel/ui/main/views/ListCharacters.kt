package com.seventhson.marvel.ui.main.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.ui.common.views.ErrorDialog
import com.seventhson.marvel.ui.common.views.SpinnerCircular
import com.seventhson.marvel.ui.main.MainViewModel
import com.seventhson.marvel.utils.extensions.isScrolledToTheEnd

@Composable
fun ListCharacters(
    viewModel: MainViewModel = hiltViewModel(),
    onItemClick: (CharacterDetail) -> Unit
) {
    val scrollState = rememberLazyListState()
    val state = viewModel.characterListState
    val stateRefreshing = viewModel.isRefreshing
    val loadMore by remember {
        derivedStateOf {
            scrollState.isScrolledToTheEnd()
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = stateRefreshing.value),
        onRefresh = {
            viewModel.refreshCharacterList()
        }
    ) {
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
        ) {

            // Character items
            items(state) { character ->
                Item(character, onItemClick)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Loading more item
            item {
                SpinnerCircular()
            }

        }

    }

    // Entra dentro del launchedeffect cada vez que loadMore cambia
    // y luego solo si es true, llama al viewmodel
    LaunchedEffect(loadMore) {
        if (loadMore)
            viewModel.reloadCharacterList()
    }

    ErrorDialog(viewModel.errorMessage)

}