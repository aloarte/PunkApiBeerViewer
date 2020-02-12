package com.p4r4d0x.punkapibeerviewer.model


import com.google.gson.annotations.SerializedName


data class Temp(
    @SerializedName("unit")
    val unit: String = "",
    @SerializedName("value")
    val value: Int = 0
)


data class HopsItem(
    @SerializedName("add")
    val add: String = "",
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("attribute")
    val attribute: String = ""
)


data class Volume(
    @SerializedName("unit")
    val unit: String = "",
    @SerializedName("value")
    val value: Int = 0
)


data class MashTempItem(
    @SerializedName("duration")
    val duration: Int = 0,
    @SerializedName("temp")
    val temp: Temp
)


data class Amount(
    @SerializedName("unit")
    val unit: String = "",
    @SerializedName("value")
    val value: Double = 0.0
)


data class Ingredients(
    @SerializedName("hops")
    val hops: List<HopsItem>?,
    @SerializedName("yeast")
    val yeast: String = "",
    @SerializedName("malt")
    val malt: List<MaltItem>?
)

data class BeerDtoWrapper(
    val listBeer: List<BeerDTO>
)

data class BeerDTO(
    @SerializedName("first_brewed")
    val firstBrewed: String = "",
    @SerializedName("attenuation_level")
    val attenuationLevel: Double = 0.0,
    @SerializedName("method")
    val method: Method,
    @SerializedName("target_og")
    val targetOg: Double = 0.0,
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume,
    @SerializedName("ebc")
    val ebc: Double = 0.0,
    @SerializedName("description")
    val description: String = "",
    @SerializedName("target_fg")
    val targetFg: Double = 0.0,
    @SerializedName("srm")
    val srm: Double = 0.0,
    @SerializedName("volume")
    val volume: Volume,
    @SerializedName("contributed_by")
    val contributedBy: String = "",
    @SerializedName("abv")
    val abv: Double = 0.0,
    @SerializedName("food_pairing")
    val foodPairing: List<String>?,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("ph")
    val ph: Double = 0.0,
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("ingredients")
    val ingredients: Ingredients,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("ibu")
    val ibu: Double = 0.0,
    @SerializedName("brewers_tips")
    val brewersTips: String = ""
)


data class MaltItem(
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("name")
    val name: String = ""
)


data class Method(
    @SerializedName("mash_temp")
    val mashTemp: List<MashTempItem>?,
    @SerializedName("fermentation")
    val fermentation: Fermentation,
    @SerializedName("twist")
    val twist: String
)


data class Fermentation(
    @SerializedName("temp")
    val temp: Temp
)


data class BoilVolume(
    @SerializedName("unit")
    val unit: String = "",
    @SerializedName("value")
    val value: Int = 0
)




