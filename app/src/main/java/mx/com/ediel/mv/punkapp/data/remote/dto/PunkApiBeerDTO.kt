package mx.com.ediel.mv.punkapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import mx.com.ediel.mv.punkapp.data.models.Beer
import mx.com.ediel.mv.punkapp.data.models.Ingredient

data class PunkApiBeerDTO(
    @SerializedName("id"                ) var id               : Int?              = null,
    @SerializedName("name"              ) var name             : String?           = null,
    @SerializedName("tagline"           ) var tagline          : String?           = null,
    @SerializedName("first_brewed"      ) var firstBrewed      : String?           = null,
    @SerializedName("description"       ) var description      : String?           = null,
    @SerializedName("image_url"         ) var imageUrl         : String?           = null,
    @SerializedName("ingredients"       ) var ingredients      : Ingredients?      = Ingredients(),
    @SerializedName("food_pairing"      ) var foodPairing      : ArrayList<String> = arrayListOf(),
    @SerializedName("brewers_tips"      ) var brewersTips      : String?           = null,
    @SerializedName("volume"            ) var volume           : Volume?           = Volume(),
    @SerializedName("abv"               ) var abv              : Double?           = null,
    @SerializedName("ibu"               ) var ibu              : Double?              = null,
    @SerializedName("target_fg"         ) var targetFg         : Double?              = null,
    @SerializedName("target_og"         ) var targetOg         : Double?              = null,
){
    fun toBeer(): Beer {
        val ingredientsList: MutableList<Ingredient> = mutableListOf()

        ingredientsList.add(
            Ingredient(
                title = "Malt",
                items = ingredients?.malt?.map { "${it.name} ${it.amount?.value} ${it.amount?.unit}" } ?: emptyList()
            )
        )
        ingredientsList.add(
            Ingredient(
                title = "Hops",
                items = ingredients?.hops?.map { "${it.name} ${it.amount?.value} ${it.amount?.unit}" } ?: emptyList()
            )
        )

        return Beer(
            id = id ?: 0,
            name = name ?: "",
            tagline = tagline ?: "",
            imageUrl = imageUrl ?: "",
            volume = "${volume?.value} ${volume?.unit}",
            abv = "$abv",
            ibu = "$ibu",
            og = "$targetOg",
            fg = "$targetFg",
            yeast = "${ingredients?.yeast}",
            firstBrewed = "$firstBrewed",
            brewersTips = "$brewersTips",
            foodPairing = foodPairing,
            ingredients = ingredientsList
        )
    }
}

data class Volume (

    @SerializedName("value" ) var value : Int?    = null,
    @SerializedName("unit"  ) var unit  : String? = null

)

data class BoilVolume (

    @SerializedName("value" ) var value : Int?    = null,
    @SerializedName("unit"  ) var unit  : String? = null

)

data class MashTemp (

    @SerializedName("temp"     ) var temp     : Temp? = Temp(),
    @SerializedName("duration" ) var duration : Int?  = null

)

data class Temp (

    @SerializedName("value" ) var value : Int?    = null,
    @SerializedName("unit"  ) var unit  : String? = null

)

data class Fermentation (

    @SerializedName("temp" ) var temp : Temp? = Temp()

)

data class Method (

    @SerializedName("mash_temp"    ) var mashTemp     : ArrayList<MashTemp> = arrayListOf(),
    @SerializedName("fermentation" ) var fermentation : Fermentation?       = Fermentation(),
    @SerializedName("twist"        ) var twist        : String?             = null

)

data class Amount (

    @SerializedName("value" ) var value : Double? = null,
    @SerializedName("unit"  ) var unit  : String? = null

)

data class Malt (

    @SerializedName("name"   ) var name   : String? = null,
    @SerializedName("amount" ) var amount : Amount? = Amount()

)

data class Hops (

    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("amount"    ) var amount    : Amount? = Amount(),
    @SerializedName("add"       ) var add       : String? = null,
    @SerializedName("attribute" ) var attribute : String? = null

)

data class Ingredients (

    @SerializedName("malt"  ) var malt  : ArrayList<Malt> = arrayListOf(),
    @SerializedName("hops"  ) var hops  : ArrayList<Hops> = arrayListOf(),
    @SerializedName("yeast" ) var yeast : String?         = null

)
