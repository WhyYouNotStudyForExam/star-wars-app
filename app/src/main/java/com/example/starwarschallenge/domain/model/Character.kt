package com.example.starwarschallenge.domain.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("height") val height: Double? = 0.0,
    @SerializedName("mass") val mass: Int? = 0,
    @SerializedName("gender") val gender: String = "",
    @SerializedName("homeworld") val homeworld: List<String>? = emptyList(),
    @SerializedName("wiki") val wiki: String? = "",
    @SerializedName("image") val image: String? = "",
    @SerializedName("born") val born: String? = "",
    @SerializedName("bornLocation") val bornLocation: String? = "",
    @SerializedName("died") val died: Int? = 0,
    @SerializedName("diedLocation") val diedLocation: String? = "",
    @SerializedName("species") val species: String? = "",
    @SerializedName("hairColor") val hairColor: String? = "",
    @SerializedName("eyeColor") val eyeColor: String? = "",
    @SerializedName("skinColor") val skinColor: String? = "",
    @SerializedName("cybernetics") val cybernetics: List<String>? = emptyList(),
    @SerializedName("affiliations") val affiliations: List<String>? = emptyList(),
    @SerializedName("masters") val masters: List<String>? = emptyList(),
    @SerializedName("apprentices") val apprentices: List<String>? = emptyList(),
    @SerializedName("formerAffiliations") val formerAffiliations: List<String>? = emptyList(),
    @SerializedName("dateCreated") val dateCreated: Int? = 0,
    @SerializedName("dateDestroyed") val dateDestroyed: Int? = 0,
    @SerializedName("destroyedLocation") val destroyedLocation: String? = "",
    @SerializedName("creator") val creator: String? = "",
    @SerializedName("manufacturer") val manufacturer: String? = "",
    @SerializedName("model") val model: String? = "",
    @SerializedName("class") val classType: String? = "",
    @SerializedName("sensorColor") val sensorColor: String? = "",
    @SerializedName("platingColor") val platingColor: String? = "",
    @SerializedName("equipment") val equipment: List<String>? = emptyList()
)