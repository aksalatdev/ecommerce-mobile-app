package com.example.ecom.repos

import com.example.ecom.model.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class ProductRepository {

    private fun retrofit(): EcommerceApi {
        return Retrofit.Builder()
            .baseUrl("https://scndapi-v1.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(EcommerceApi::class.java)
    }

    suspend fun fetchAllProductsRetrofit(): List<Product> {
        return retrofit().fetchAllProducts()
    }

    suspend fun fetchProduct(productTitle: String): Product{
        return fetchAllProductsRetrofit().first { it.title == productTitle }
    }


//    fun getAllProducts(): Single<List<Product>> {
//        return Single.create<List<Product>> {
//            it.onSuccess(fetchProducts())
//        }
//    }


//    fun searchForProduct(term: String){
//
//    }
//    fun getProductPhotos(){
//
//    }

    suspend fun searchForProducts(term: String): List<Product> {
        return fetchAllProductsRetrofit().filter { it.title.contains(term, true) }
    }

//    fun getProductsByName(name: String?): Single<Product> {
//        return Single.create<Product> {
//            val product = fetchProducts().first { it.title == name }
//            it.onSuccess(product)
//        }
//    }

}