package mx.com.ediel.mv.punkapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PunkAPIResponse (

    @SerializedName("id"                ) var id               : Int?              = null,
    @SerializedName("name"              ) var name             : String?           = null,
    @SerializedName("tagline"           ) var tagline          : String?           = null,
    @SerializedName("first_brewed"      ) var firstBrewed      : String?           = null,
    @SerializedName("description"       ) var description      : String?           = null,
    @SerializedName("image_url"         ) var imageUrl         : String?           = null,
    @SerializedName("abv"               ) var abv              : Double?           = null,
    @SerializedName("ibu"               ) var ibu              : Int?              = null,
    @SerializedName("target_fg"         ) var targetFg         : Int?              = null,
    @SerializedName("target_og"         ) var targetOg         : Int?              = null,
    @SerializedName("ebc"               ) var ebc              : Int?              = null,
    @SerializedName("srm"               ) var srm              : Int?              = null,
    @SerializedName("ph"                ) var ph               : Double?           = null,
    @SerializedName("attenuation_level" ) var attenuationLevel : Int?              = null,
    @SerializedName("volume"            ) var volume           : Volume?           = Volume(),
    @SerializedName("boil_volume"       ) var boilVolume       : BoilVolume?       = BoilVolume(),
    @SerializedName("method"            ) var method           : Method?           = Method(),
    @SerializedName("ingredients"       ) var ingredients      : Ingredients?      = Ingredients(),
    @SerializedName("food_pairing"      ) var foodPairing      : ArrayList<String> = arrayListOf(),
    @SerializedName("brewers_tips"      ) var brewersTips      : String?           = null,
    @SerializedName("contributed_by"    ) var contributedBy    : String?           = null

)