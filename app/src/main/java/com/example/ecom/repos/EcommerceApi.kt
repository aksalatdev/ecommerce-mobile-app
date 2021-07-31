package com.example.ecom.repos

import com.example.ecom.model.Product
import retrofit2.http.GET

interface EcommerceApi {

    @GET("api/product")
    suspend fun fetchAllProducts(): List<Product>


}