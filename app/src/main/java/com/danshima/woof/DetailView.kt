package com.danshima.woof

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import com.danshima.woof.models.Cat
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Add
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
import com.danshima.woof.ui.theme.PrimaryColor
import com.danshima.woof.ui.theme.Purple500


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
                    CatDescription(cat = cat, height = this@BoxWithConstraints.maxHeight)
                }
            }
            AddCatButton(Modifier.align(Alignment.BottomEnd), isExtended = scrollState.value == 0)
        }
    }
}

@Composable
private fun CatHeader(
    scrollState: ScrollState,
    cat: Cat,
    height: Dp
) {
    val offset = (scrollState.value / 2)
    // makes the image smaller when you scroll down
    val offsetDp = with(LocalDensity.current) {
        offset.toDp()
    }
    Image(
        modifier = Modifier
            .heightIn(max = height / 2)
            .fillMaxWidth()
            .padding(all = offsetDp),
        painter = painterResource(id = cat.imageId),
        contentScale = ContentScale.Fit,
        contentDescription = "cat image"
    )
}

@Composable
private fun CatDescription(cat: Cat, height: Dp) {
    Column() {
        Spacer(modifier = Modifier.height(8.dp))
        CatName(cat = cat)
        CatDetail(stringResource(id = R.string.cat_description), cat.description)
        Spacer(Modifier.height((height - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun CatName(cat: Cat) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Text(
            text = cat.name,
            modifier = Modifier.requiredHeight(34.dp),
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CatDetail(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(text = label, modifier = Modifier.height(24.dp), style = MaterialTheme.typography.caption)
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(text = value, modifier = Modifier.height(24.dp), style = style)
    }
}

@Composable
fun AddCatButton(modifier: Modifier = Modifier, isExtended: Boolean) {
    FloatingActionButton(
        onClick = {

        },
        modifier = modifier
            .padding(16.dp)
            .height(50.dp)
            .widthIn(min = 50.dp),
        backgroundColor = PrimaryColor,
        contentColor = Color.White
    ) {
        //Icon(Icons.Filled.Add,"")
        AnimatedFab(icon = { Icon(imageVector = Icons.Outlined.Add, null) },
            text = { Text(text = "Add more") }, isExtended = isExtended)
    }
}

@Preview
@Composable
fun DetailPreview() {
    val cat = CatRepository().cats.first()
    showCat(cat = cat)
}