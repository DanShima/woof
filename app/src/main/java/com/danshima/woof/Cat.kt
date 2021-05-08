package com.danshima.woof

data class Cat(
    val id: Int,
    val name: String,
    val description: String,
    val imageId: Int = R.drawable.ic_cutie
)
