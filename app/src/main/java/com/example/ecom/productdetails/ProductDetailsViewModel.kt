package com.example.ecom.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecom.model.Product
import com.example.ecom.repos.ProductRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ProductDetailsViewModel : ViewModel() {

    val productDetails  = MutableLiveData<Product>()

    fun fetchProductDetails(productTitle: String) {
        viewModelScope.launch(Dispatchers.Default) {
//            val json =
//                URL("https://gist.githubusercontent.com/wahyubudies/cbbeed1aa0dc4567ab83b044ef9f4cf2/raw/720b6718c1e549a206cdfadee077a47ffb4e9c84/shopping_products.json").readText()
//            val list = Gson().fromJson(json, Array<Product>::class.java).toList()
//            val product = list.first { it.title == productTitle }

            productDetails.postValue(ProductRepository().fetchProduct(productTitle))

        }
    }
}