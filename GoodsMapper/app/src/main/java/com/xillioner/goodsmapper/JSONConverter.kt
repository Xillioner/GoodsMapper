package com.xillioner.goodsmapper

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONStringer
import org.json.JSONTokener

class JSONConverter() {
    @Throws(Exception::class,JSONException::class)
    fun convertToString(tovars: ArrayList<Tovar>):String{
        val jArray=JSONArray()
        for(tovar in tovars)
            jArray.put(tovar.convertToJSON())
        return jArray.toString(0)
    }
    @Throws(Exception::class,JSONException::class)
    fun convertToTovar(jsonString:String):ArrayList<Tovar>{
        val tovarList=ArrayList<Tovar>()
        val jArray=JSONTokener(jsonString).nextValue() as JSONArray
        for(item in 0 until jArray.length()){
            tovarList.add(Tovar(jArray.getJSONObject(item)))
        }

        return tovarList
    }
}