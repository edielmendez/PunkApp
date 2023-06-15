package mx.com.ediel.mv.punkapp.data.local.favorite

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mx.com.ediel.mv.punkapp.data.local.dao.FavoriteDAO
import mx.com.ediel.mv.punkapp.data.models.Favorite
import mx.com.ediel.mv.punkapp.di.DefaultDispatcher
import mx.com.ediel.mv.punkapp.ui.main.MainFragment
import javax.inject.Inject

class FavoriteLocalRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val dao: FavoriteDAO
): FavoriteLocalRepository {
    override suspend fun getFavorite(favId: Int) = withContext(dispatcher) {
        dao.getFavorite(favId)?.toFavorite()
    }

    override suspend fun getFavorites() = withContext(dispatcher) {
        dao.getFavorites().map { favorites -> favorites.map { it.toFavorite() } }
    }

    override suspend fun saveFavorite(favorite: Favorite) = withContext(dispatcher) {
        Log.v(MainFragment.TAG, " - Repository  saveFavorite")
        dao.saveFavorite(favorite.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(favorite: Favorite) = withContext(dispatcher) {
        Log.v(MainFragment.TAG, " - Repository  deleteFavorite")
        dao.deleteFavorite(favorite.toFavoriteEntity())
    }

}