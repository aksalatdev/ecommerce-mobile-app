package com.example.ecom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecom.model.Product
import com.example.ecom.repos.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class   MainFragmentViewModel : ViewModel() {

    val products = MutableLiveData<List<Product>>()

    fun setup(){
        viewModelScope.launch(Dispatchers.Default){
            products.postValue(ProductRepository().fetchAllProductsRetrofit())

        }

    }

    fun search(term: String) {
        viewModelScope.launch(Dispatchers.Default) {
            products.postValue(ProductRepository().searchForProducts(term))
        }
    }
}