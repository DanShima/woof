package com.danshima.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danshima.woof.ui.theme.WoofTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.core.content.ContextCompat
import com.danshima.woof.models.Cat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        WoofTheme {
            Surface(color = MaterialTheme.colors.background) {
                Overview {
                    ContextCompat.startActivity(this, DetailActivity.newIntent(this, it), null)
                }
            }
        }
    }

    @Composable
    fun Overview(navigateToDetail: (Cat) -> Unit) {
        Scaffold(
            content = {
                CatList(goToDetail = navigateToDetail)
            }
        )
    }


    @Composable
    fun CatList(goToDetail: (Cat) -> Unit) {
        val cats = remember { CatRepository().cats }
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(
                items = cats,
                itemContent = {
                    CatListItem(cat = it, goToDetail = goToDetail)
                }
            )
        }
    }
}

