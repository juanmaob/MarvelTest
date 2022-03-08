package com.seventhson.marvel.ui.detail.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.seventhson.marvel.ui.common.views.ErrorDialog
import com.seventhson.marvel.ui.common.views.Loading
import com.seventhson.marvel.ui.detail.DetailViewModel

@ExperimentalPagerApi
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onClickBack: () -> Unit
) {

    val state = viewModel.characterDetailState

    Column {
        DetailHeader(state.value.name, state.value.image, onClickBack)
        DetailInfo(state)
    }

    ErrorDialog(viewModel.errorMessage)
    Loading(isLoading = viewModel.loading)

}
