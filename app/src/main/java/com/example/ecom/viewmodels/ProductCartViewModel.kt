package com.example.ecom.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecom.model.Product_cart

class ProductCartViewModel : ViewModel() {
    private var products = MutableLiveData<List<Product_cart>>()
    private var selectedProducts = MutableLiveData<List<Product_cart>>()

    init {
        products.postValue(mutableListOf())
        selectedProducts.postValue(mutableListOf())
    }

    fun fetchDummyProduct() {
        //api
        val dummies = mutableListOf<Product_cart>().apply {
            add(Product_cart(1, "asdasd", 3000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt.jpg"))
            add(Product_cart(2, "beng", 3000, "https://pbs.twimg.com/profile_images/1210618202457292802/lt9KD2lt.jpg"))
        }
        products.postValue(dummies)
    }

    fun addSelectedProduct(productCart: Product_cart) {
        val tempSelectedProducts: MutableList<Product_cart> = if (selectedProducts.value == null) {
            mutableListOf()
        } else {
            selectedProducts.value as MutableList<Product_cart>
        }
        val sameProduct_cart: Product_cart? = tempSelectedProducts.find { p ->
            p.id == productCart.id
        }
        sameProduct_cart?.let {
            it.selectedQuantity = it.selectedQuantity?.plus(1)
        } ?: kotlin.run {
            tempSelectedProducts.add(productCart)
        }
        selectedProducts.postValue(tempSelectedProducts)

    }

    fun decrementQuantityProduct(productCart: Product_cart) {
        val tempSelectedProducts: MutableList<Product_cart> = if (selectedProducts.value == null) {
            mutableListOf()
        } else {
            selectedProducts.value as MutableList<Product_cart>
        }
        val sameProduct_cart: Product_cart? = tempSelectedProducts.find { p ->
            p.id == productCart.id
        }
        sameProduct_cart?.let {
            if (it.selectedQuantity?.minus(1) == 0) {
                tempSelectedProducts.remove(it)
            } else {
                it.selectedQuantity = it.selectedQuantity!!.minus(1)
            }
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun incrementQuantityProduct(productCart: Product_cart) {
        val tempSelectedProducts: MutableList<Product_cart> = if (selectedProducts.value == null) {
            mutableListOf()
        } else {
            selectedProducts.value as MutableList<Product_cart>
        }
        val sameProduct_cart: Product_cart? = tempSelectedProducts.find { p ->
            p.id == productCart.id
        }
        sameProduct_cart?.let {
            it.selectedQuantity = it.selectedQuantity!!.plus(1)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun deletedSelectedProduct(productCart: Product_cart) {
        val tempSelectedProducts: MutableList<Product_cart> = if (selectedProducts.value == null) {
            mutableListOf()
        } else {
            selectedProducts.value as MutableList<Product_cart>
        }
        val sameProduct_cart: Product_cart? = tempSelectedProducts.find { p ->
            p.id == productCart.id
        }
        sameProduct_cart?.let {
            tempSelectedProducts.remove(it)
        }
        selectedProducts.postValue(tempSelectedProducts)
    }

    fun listenToProducts() = products
    fun listToSelectedProducts() = selectedProducts

}