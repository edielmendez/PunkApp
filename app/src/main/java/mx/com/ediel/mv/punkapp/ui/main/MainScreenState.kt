package mx.com.ediel.mv.punkapp.ui.main

import mx.com.ediel.mv.punkapp.data.models.Beer

data class MainScreenState(
    val beers: MutableList<Beer> = mutableListOf(),
    val positionChanged: Int? = null
)
