package mx.com.ediel.mv.punkapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.com.ediel.mv.punkapp.data.local.entities.UserEntity

@Dao
interface UserDAO {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(int: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveUser(user: UserEntity)
}