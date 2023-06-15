package mx.com.ediel.mv.punkapp.data.models

data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val imageUrl: String,
    val volume: String,
    val abv: String,
    val ibu: String,
    val og: String,
    val fg: String,
    val yeast: String,
    val firstBrewed: String,
    val brewersTips: String,
    val foodPairing: List<String>,
    val ingredients: List<Ingredient>,
    val isFavorite: Boolean = false
){
    fun toFavorite() = Favorite(
        id = id,
        name = name,
        tagline = tagline,
        imageUrl = imageUrl,
        rate = 0
    )
}
