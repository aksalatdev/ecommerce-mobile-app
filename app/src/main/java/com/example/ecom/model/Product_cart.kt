package com.example.ecom.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Product_cart (
    var id: Int? = null,
    var name : String? = null,
    var price : Int? = null,
    var image : String? = null,
    var selectedQuantity : Int?  = null

    ): Parcelable