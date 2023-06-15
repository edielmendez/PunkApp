package mx.com.ediel.mv.punkapp.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.punkapp.data.local.entities.FavoriteEntity

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorites WHERE id = :favId")
    suspend fun getFavorite(favId: Int): FavoriteEntity?

    @Query("SELECT * FROM favorites")
    fun getFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)
}