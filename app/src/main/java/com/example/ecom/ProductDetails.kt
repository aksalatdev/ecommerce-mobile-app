package com.example.ecom

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ecom.cart.CartActivity
import com.example.ecom.productdetails.ProductDetailsViewModel
import com.example.ecom.repos.ProductRepository
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.product_details.*

class   ProductDetails : AppCompatActivity() {

    lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (super.onCreate(savedInstanceState))
        setContentView(R.layout.product_details)

        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)

        val title = intent.getStringExtra("title") ?: ""

        viewModel.productDetails.observe(this, Observer {
            product_name.text = it.title
            Picasso.get().load(it.photoUrl).into(photo)
            thePriceOfProduct.text = "$${it.price}"
            descriptionProduct.text = it.description
        })

        viewModel.fetchProductDetails(title)

        addToCartButton.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

//        val photoUrl = intent.getStringExtra("photo_url")
//        val product = ProductRepository().getProductsByName(title)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//
//                product_name.text = it.title
//                Picasso.get().load(it.photoUrl).into(photo)
//                thePriceOfProduct.text = "$${it.price}"
//
//            }, {})



        availability.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("Hai, Stok $title Tersedia")
                    .setPositiveButton("OK") { dialog, which ->

                    }
                    .create()
                    .show()
        }
    }
}