package com.example.ecom.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.ecom.R
import com.example.ecom.model.Product_cart
import com.example.ecom.viewmodels.ProductCartViewModel
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductAdapter(
    private var products: MutableList<Product_cart>,
    private var context: Context,
    private var productCartViewModel: ProductCartViewModel
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(products[position], context, productCartViewModel)

    override fun getItemCount() = products.size

    fun updateList(ps: List<Product_cart>) {
        products.clear()
        products.addAll(ps)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            productCart: Product_cart,
            context: Context,
            productCartViewModel: ProductCartViewModel
        ) {
                itemView.product_namecart.text = productCart.name
                itemView.product_price.text =  productCart.price.toString()
                itemView.product_image.load(productCart.image)
                    val p = productCart.copy()
                    p.selectedQuantity = 1
                    productCartViewModel.addSelectedProduct(p)
        }
    }

}