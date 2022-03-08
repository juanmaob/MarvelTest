package com.seventhson.marvel.ui.detail.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seventhson.marvel.R
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.ui.common.views.KeyValueInfo

@Composable
fun DetailInfoPage(characterDetail: CharacterDetail) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) { KeyValueInfo(stringResource(R.string.comics_num), characterDetail.comicsNum) }
            Box(modifier = Modifier.weight(1f)) { KeyValueInfo(stringResource(R.string.modified_date), characterDetail.modifiedDate) }
        }
        KeyValueInfo(stringResource(R.string.description), characterDetail.description)
    }
}

@Preview
@Composable
fun DetailInfoPagePreview() {
    DetailInfoPage(
        CharacterDetail(
            id = 1,
            name = "Example",
            modifiedDate = "2000",
            comicsNum = "23",
            description = "Lorem Ipsum",
            image = ""

        )
    )
}
