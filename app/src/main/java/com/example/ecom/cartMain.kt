package com.example.ecom

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecom.adapters.ProductAdapter
import com.example.ecom.adapters.SelectedProductAdapter
import com.example.ecom.viewmodels.ProductCartViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottomsheet_cart.*
import kotlinx.android.synthetic.main.main_cart.*

class cartMain : AppCompatActivity() {
    private lateinit var productCartViewModel: ProductCartViewModel
    private lateinit var bottomSheet: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_cart)
        setupUI()
    }

    private fun setupUI() {
        productCartViewModel = ViewModelProvider(this).get(productCartViewModel::class.java)

        rv_product.apply {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    GridLayoutManager(this@cartMain, 2)
                } else {
                    GridLayoutManager(this@cartMain, 4)
                }
            adapter = ProductAdapter(mutableListOf(), this@cartMain, productCartViewModel)
        }
        rv_selected_product.apply {
            layoutManager = LinearLayoutManager(this@cartMain)
            adapter = SelectedProductAdapter(mutableListOf(), this@cartMain, productCartViewModel)
        }
        bottomSheet = BottomSheetBehavior.from(bottomsheet_detail_order)
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        btn_detail.setOnClickListener {
            if (bottomSheet.state == BottomSheetBehavior.STATE_COLLAPSED || bottomSheet.state == BottomSheetBehavior.STATE_HIDDEN){
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }else{
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        }
        btn_checkout.setOnClickListener {
            Toast.makeText(this@cartMain, "Checkout", Toast.LENGTH_LONG).show()
        }
        productCartViewModel.fetchDummyProduct()
        productCartViewModel.listenToProducts().observe(this, Observer {
            rv_product.adapter?.let { a ->
                if(a is ProductAdapter){
                    a.updateList(it)
                }
            }
        })
        productCartViewModel.listToSelectedProducts().observe(this, Observer {
                rv_selected_product.adapter?.let{ a->
                    if (a is SelectedProductAdapter){
                        a.updateList(it)
                    }
                }
                val totalPrice = if (it.isEmpty()){0}else{
                    it.sumBy { p ->
                        p.price!! * p.selectedQuantity!!
                    }
                }
            total_price.text = total_price.toString()
        })
    }

}

