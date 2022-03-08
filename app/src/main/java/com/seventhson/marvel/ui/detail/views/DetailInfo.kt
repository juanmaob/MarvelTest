package com.seventhson.marvel.ui.detail.views
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.seventhson.marvel.domain.common.CharacterDetail

@ExperimentalPagerApi
@Composable
fun DetailInfo(
    state: State<CharacterDetail?>
) {
    state.value?.let { detail ->
        DetailInfoView(detail)
    }

}

@Preview
@ExperimentalPagerApi
@Composable
fun DetailInfoView(
    characterDetail: CharacterDetail = CharacterDetail()
) {
    Column(modifier = Modifier.fillMaxSize()) {

        DetailInfoPage(characterDetail)

    }
}
