package mx.com.ediel.mv.punkapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.com.ediel.mv.punkapp.data.local.dao.FavoriteDAO
import mx.com.ediel.mv.punkapp.data.local.dao.UserDAO
import mx.com.ediel.mv.punkapp.data.local.entities.FavoriteEntity
import mx.com.ediel.mv.punkapp.data.local.entities.UserEntity

@Database(entities = [UserEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun favoriteDAO(): FavoriteDAO

    companion object {
        private const val DB_NAME = "PUNK_APP_DB_ROOM.db"

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .createFromAsset("db/PUNK_APP_DB_ROOM_db.db")
                .build()
        }
    }
}