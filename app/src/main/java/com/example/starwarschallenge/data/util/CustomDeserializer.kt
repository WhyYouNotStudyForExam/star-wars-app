import com.example.starwarschallenge.domain.model.Character
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class CustomDeserializer : JsonDeserializer<Character> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Character {
        val jsonObject = json.asJsonObject

        // Beispiel, wie man einen Wert mit einem Default-Wert deserialisiert, wenn er nicht vorhanden ist
        val id = jsonObject.get("id").asInt
        val name = jsonObject.get("name").asString
        val height = jsonObject.get("height")?.asDouble
        val mass = jsonObject.get("mass")?.asInt
        val gender = jsonObject.get("gender").asString
        val homeworld = deserializeToListOrString(context, jsonObject.get("homeworld"))
        val wiki = jsonObject.get("wiki")?.asString
        // Das Bildfeld wird ignoriert, da es auskommentiert ist
        val born = jsonObject.get("born")?.asString
        val bornLocation = jsonObject.get("bornLocation")?.asString
        val died = jsonObject.get("died")?.asInt
        val diedLocation = jsonObject.get("diedLocation")?.asString
        val species = jsonObject.get("species")?.asString
        val hairColor = jsonObject.get("hairColor")?.asString
        val eyeColor = jsonObject.get("eyeColor")?.asString
        val skinColor = jsonObject.get("skinColor")?.asString
        val cybernetics = deserializeToListOrString(context, jsonObject.get("cybernetics"))
        val affiliations = deserializeToListOrString(context, jsonObject.get("affiliations"))
        val masters = deserializeToListOrString(context, jsonObject.get("masters"))
        val apprentices = deserializeToListOrString(context, jsonObject.get("apprentices"))
        val formerAffiliations =
            deserializeToListOrString(context, jsonObject.get("formerAffiliations"))
        val dateCreated = jsonObject.get("dateCreated")?.asInt
        val dateDestroyed = jsonObject.get("dateDestroyed")?.asInt
        val destroyedLocation = jsonObject.get("destroyedLocation")?.asString
        val creator = jsonObject.get("creator")?.asString
        val manufacturer = jsonObject.get("manufacturer")?.asString
        val model = jsonObject.get("model")?.asString
        val classType = jsonObject.get("class")?.asString
        val sensorColor = jsonObject.get("sensorColor")?.asString
        val platingColor = jsonObject.get("platingColor")?.asString
        val equipment = deserializeToListOrString(context, jsonObject.get("equipment"))

        return Character(
            id,
            name,
            height,
            mass,
            gender,
            homeworld,
            wiki,
            born,
            bornLocation,
            died,
            diedLocation,
            species,
            hairColor,
            eyeColor,
            skinColor,
            cybernetics,
            affiliations,
            masters,
            apprentices,
            formerAffiliations,
            dateCreated,
            dateDestroyed,
            destroyedLocation,
            creator,
            manufacturer,
            model,
            classType,
            sensorColor,
            platingColor,
            equipment
        )
    }

    private fun deserializeToListOrString(
        context: JsonDeserializationContext,
        jsonElement: JsonElement?
    ): List<String>? {
        jsonElement ?: return null

        return when {
            jsonElement.isJsonArray -> context.deserialize(
                jsonElement,
                object : TypeToken<List<String>>() {}.type
            )

            jsonElement.isJsonPrimitive -> listOf(jsonElement.asString)
            else -> null
        }
    }
}
