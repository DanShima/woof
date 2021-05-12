package com.danshima.woof

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.danshima.woof.models.Cat

@Composable
fun CatListItem(cat: Cat, goToDetail:(Cat) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(Modifier.clickable { goToDetail(cat) }) {
            loadImage(cat = cat)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = cat.name, style = MaterialTheme.typography.h6)
                Text(text = "See more...", style = MaterialTheme.typography.caption)
            }
        }
    }
}

@Composable
private fun loadImage(cat: Cat) {
    Image(
        painter = painterResource(id = cat.imageId), 
        contentDescription = cat.name, 
        contentScale = ContentScale.Crop, 
        modifier = Modifier
            .padding(8.dp)
            .size(60.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp))))
}