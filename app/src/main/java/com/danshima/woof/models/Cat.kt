package com.danshima.woof.models

import com.danshima.woof.R
import java.io.Serializable

data class Cat(
    val id: Int,
    val name: String,
    val description: String,
    val imageId: Int = R.drawable.ic_cutie
): Serializable
