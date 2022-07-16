package com.xillioner.goodsmapper

import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

class Tovar {
    var name:String?=null
    var description:String?=null
    var price:Double?=null
    var buyStatus:String?=null
    var image:String?=null
    var amount:Int=0

    private val JSON_IMAGE="image"
    private val JSON_NAME="name"
    private val JSON_AMOUNT="amount"
    private val JSON_PRICE="price"

    @Throws(JSONException::class)
    constructor(jo:JSONObject){
        image=jo.getString(JSON_IMAGE)
        name=jo.getString(JSON_NAME)
        amount=jo.getInt(JSON_AMOUNT)
        price=jo.getDouble(JSON_PRICE)
    }
    constructor(){}

    @Throws(JSONException::class)
    fun convertToJSON():JSONObject{
        val jo=JSONObject()
        jo.put(JSON_IMAGE,image)
        jo.put(JSON_NAME,name)
        jo.put(JSON_AMOUNT,amount)
        jo.put(JSON_PRICE,price)
        return jo
    }
}