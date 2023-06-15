package mx.com.ediel.mv.punkapp.data.models

import mx.com.ediel.mv.punkapp.data.local.entities.FavoriteEntity

data class Favorite(
    val id: Int,
    val name: String,
    val tagline: String,
    val imageUrl: String,
    val rate: Int
){
    fun toFavoriteEntity() = FavoriteEntity(
        id =  id,
        name = name,
        tagline = tagline,
        imageUrl = imageUrl,
        rate = rate
    )
}
