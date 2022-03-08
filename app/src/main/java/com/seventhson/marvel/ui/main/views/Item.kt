package com.seventhson.marvel.ui.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.seventhson.marvel.R
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.ui.common.PillText
import com.seventhson.marvel.ui.theme.VeryLightGrey

@Composable
fun Item(
    characterDetail: CharacterDetail,
    onItemClick: (CharacterDetail) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .clickable {
                onItemClick(characterDetail)
            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {

            Image(
                painter = rememberImagePainter(
                    data = characterDetail.image,
                    builder = {
                        transformations(CircleCropTransformation())
                        crossfade(true)
                        error(R.drawable.ic_marvel_red)
                        placeholder(drawableResId = R.drawable.ic_marvel_red)
                    }),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(54.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .height(54.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(characterDetail.name ?: "", fontWeight = FontWeight.Bold)
                }

                Spacer(Modifier.height(8.dp))

                Row {
                    PillText(
                        characterDetail.id.toString() ?: "",
                        backgroundColor = VeryLightGrey,
                        textColor = Color.DarkGray
                    )

                }
            }
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    Item(
        characterDetail = CharacterDetail(
            id = 9867,
            name = "Thor",
            modifiedDate = "2000",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec/landscape_incredible.jpg"
        ),
        onItemClick = {}
    )
}