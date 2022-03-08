package com.seventhson.marvel.utils.extensions

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd() = if (layoutInfo.totalItemsCount > 1)
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
else
    false