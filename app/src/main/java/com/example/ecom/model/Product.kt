package com.example.ecom.model

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("name")
    val title: String,

    @SerializedName("photo_url")
    val photoUrl: String,

    val price: Double,

    val isOnSale: Boolean,

    @SerializedName("description")
    val description : String,

    @SerializedName("selectedQuantity")
    val selectedQuantity : String,


    )