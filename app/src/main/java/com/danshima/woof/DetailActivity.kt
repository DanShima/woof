package com.danshima.woof

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.danshima.woof.ui.theme.WoofTheme
import androidx.compose.material.Text
import com.danshima.woof.models.Cat

class DetailActivity : AppCompatActivity() {

    private val cat: Cat by lazy {
        intent?.getSerializableExtra(CAT_ID) as Cat
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofTheme {
                Text("Test")
            }
        }
    }

    companion object {
        private const val CAT_ID = "cat_id"
        fun newIntent(context: Context, cat: Cat) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(CAT_ID, cat)
            }
    }
}