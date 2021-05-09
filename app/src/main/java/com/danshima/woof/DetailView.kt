package com.danshima.woof

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import com.danshima.woof.models.Cat
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun showCat(cat: Cat) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface() {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)) {
                    CatHeader(scrollState = scrollState, cat = cat, height = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun CatHeader(
    scrollState: ScrollState,
    cat: Cat,
    height: Dpg
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) {
        offset.toDp()
    }
    Image(
        modifier = Modifier
            .heightIn(max = height / 2)
            .fillMaxWidth()
            .padding(all = offsetDp),
        painter = painterResource(id = cat.imageId),
        contentScale = ContentScale.Crop,
        contentDescription = "cat image"
    )
}

@Preview
@Composable
fun DetailPreview() {
    val cat = CatRepository().cats.first()
    showCat(cat = cat)
}