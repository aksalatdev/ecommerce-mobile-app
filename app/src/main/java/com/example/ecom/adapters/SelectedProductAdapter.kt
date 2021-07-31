package com.example.ecom.adapters

import android.view.View
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.ecom.R
import com.example.ecom.model.Product_cart
import com.example.ecom.viewmodels.ProductCartViewModel
import kotlinx.android.synthetic.main.component_number_picker.view.*
import kotlinx.android.synthetic.main.list_item_selected_product.view.*

class SelectedProductAdapter(
    private var selectedProduct: MutableList<Product_cart>, private var context: Context,
    private var productCartViewModel: ProductCartViewModel
) : RecyclerView.Adapter<SelectedProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_selected_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(selectedProduct[position], context, productCartViewModel)

    override fun getItemCount() = selectedProduct.size

    fun updateList(ps: List<Product_cart>){
        selectedProduct.clear()
        selectedProduct.addAll(ps)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            productCart: Product_cart,
            context: Context,
            productCartViewModel: ProductCartViewModel
        ) {
            itemView.product_namecart.text = productCart.name
            itemView.product_price.text = productCart.price.toString()
            itemView.product_selectedQuantity.text = productCart.selectedQuantity.toString()
            itemView.product_imagecart.load(productCart.image)
            itemView.product_increment.setOnClickListener {
                productCartViewModel.incrementQuantityProduct(productCart)
            }
            itemView.product_decrement.setOnClickListener {
                productCartViewModel.decrementQuantityProduct(productCart)
            }
            itemView.product_more.setOnClickListener {
                PopupMenu(context, it).apply {
                    menuInflater.inflate(R.menu.popup_menu, menu)
                    setOnMenuItemClickListener {menuItem ->
                        when(menuItem.itemId){
                            R.id.popup_delete -> {
                                productCartViewModel.deletedSelectedProduct(productCart)
                                true
                            }
                            else -> true
                        }
                    }
                }.show()
            }
        }
    }
}