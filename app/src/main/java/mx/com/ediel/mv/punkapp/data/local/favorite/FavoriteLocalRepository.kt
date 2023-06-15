package mx.com.ediel.mv.punkapp.data.local.favorite

import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.punkapp.data.models.Favorite
import mx.com.ediel.mv.punkapp.ui.common.UIState

interface FavoriteLocalRepository {
    suspend fun getFavorite(favId: Int): Favorite?
    suspend fun getFavorites(): Flow<List<Favorite>>
    suspend fun saveFavorite(favorite: Favorite)

    suspend fun updateRate(favId: Int, rate: Int)
    suspend fun deleteFavorite(favorite: Favorite)
}