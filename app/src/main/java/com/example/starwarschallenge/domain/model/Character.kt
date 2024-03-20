package com.example.starwarschallenge.domain.model

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Double?,
    @SerializedName("mass") val mass: Int?,
    @SerializedName("gender") val gender: String,
    @SerializedName("homeworld") val homeworld: String?,
    @SerializedName("wiki") val wiki: String?,
    //@SerializedName("image") val image: String?,
    @SerializedName("born") val born: Int?,
    @SerializedName("bornLocation") val bornLocation: String?,
    @SerializedName("died") val died: Int?,
    @SerializedName("diedLocation") val diedLocation: String?,
    @SerializedName("species") val species: String?,
    @SerializedName("hairColor") val hairColor: String?,
    @SerializedName("eyeColor") val eyeColor: String?,
    @SerializedName("skinColor") val skinColor: String?,
    @SerializedName("cybernetics") val cybernetics: String?,
    @SerializedName("affiliations") val affiliations: List<String>?,
    @SerializedName("masters") val masters: List<String>?,
    @SerializedName("apprentices") val apprentices: List<String>?,
    @SerializedName("formerAffiliations") val formerAffiliations: List<String>?,
    @SerializedName("dateCreated") val dateCreated: Int?,
    @SerializedName("dateDestroyed") val dateDestroyed: Int?,
    @SerializedName("destroyedLocation") val destroyedLocation: String?,
    @SerializedName("creator") val creator: String?,
    @SerializedName("manufacturer") val manufacturer: String?,
    @SerializedName("model") val model: String?,
    @SerializedName("class") val classType: String?,
    @SerializedName("sensorColor") val sensorColor: String?,
    @SerializedName("platingColor") val platingColor: String?,
    @SerializedName("equipment") val equipment: String?
)