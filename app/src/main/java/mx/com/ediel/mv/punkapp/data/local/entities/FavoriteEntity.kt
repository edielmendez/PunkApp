package mx.com.ediel.mv.punkapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.com.ediel.mv.punkapp.data.models.Favorite

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "fav_id") val fav_id: Int?,
    @ColumnInfo(name = "email") val name: String?,
    @ColumnInfo(name = "tagline") val tagline: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "rate") val rate: Int = 0
){
    fun toFavorite() = Favorite(
        id = fav_id ?: 0,
        name = name ?: "",
        tagline = tagline ?: "",
        imageUrl = imageUrl ?: "",
        rate = rate
    )
}
