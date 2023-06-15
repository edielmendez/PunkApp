package mx.com.ediel.mv.punkapp.core.ext

import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.data.models.Favorite

fun List<Beer>.toVerifiedList(favorites: List<Favorite>): List<Beer>{
    return this.map { beer ->
        var isFavorite = false
        favorites.forEach { fav ->
            if (fav.id == beer.id) {
                isFavorite = true
            }
        }
        if (isFavorite) {
            beer.copy(isFavorite = true)
        } else {
            beer
        }
    }.toMutableList()
}