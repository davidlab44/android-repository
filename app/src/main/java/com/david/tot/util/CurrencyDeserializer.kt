package com.david.tot.util

import com.david.tot.domain.model.ConsumibleHeader
import com.david.tot.domain.model.ManyConsumibleHeader
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class CurrencyDeserializer : JsonDeserializer<ManyConsumibleHeader> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ManyConsumibleHeader {
        if (json == null || context == null) {
            // handle error here
            throw Exception("Error")
        }
        val obj = json.asJsonObject
        /*
        // let Gson handle the other 3 properties
        val success = context.deserialize<Boolean?>(obj.get("success"), Boolean::class.java)
        val base = context.deserialize<String?>(obj.get("base"), String::class.java)
        val date = context.deserialize<Date?>(obj.get("date"), Date::class.java)
        */
        // create List<Rate> from the rates JsonObject
        val ratesSet = obj.get("consumibleList").asJsonObject.entrySet()
        val ratesList = ratesSet.map {
            //val code = it.key
            val code = it.key
            val value = it.value.asFloat
            ConsumibleHeader(0,"DAVID MOLINA","DJS564","OK","2023-08-10T01:42:45.655Z",0)
        }
        return ManyConsumibleHeader(ratesList)
    }
}