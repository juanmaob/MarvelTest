package com.seventhson.marvel.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.seventhson.marvel.ui.theme.MarvelTheme

@Composable
fun MarvelCompose(
    content: @Composable () -> Unit
) {
    MarvelTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}