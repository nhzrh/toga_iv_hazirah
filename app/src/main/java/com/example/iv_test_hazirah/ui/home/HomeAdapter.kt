package com.example.iv_test_hazirah.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iv_test_hazirah.R
import com.example.iv_test_hazirah.data.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_product.view.*

class HomeAdapter (
    private val list: Array<Product>,
    private val context: Context,
    private val listener: OnViewClickListener
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    interface OnViewClickListener {
        fun onViewClick(data: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listItem: LinearLayout = view.listItem
        val closeOverlay: FrameLayout = view.closeOverlay
        val imgProduct: ImageView = view.imgProduct
        val txtProductName: TextView = view.txtProductName
        val txtDesc: TextView = view.txtDesc
        val txtCloseLabel: TextView = view.txtCloseLabel
        val txtStar: TextView = view.txtStar
        val txtDistance: TextView = view.txtDistance
        val txtPromoDesc: TextView = view.txtPromoDesc
        val txtOutlet: TextView = view.txtOutlet
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        Picasso.with(context).load(product.imageUrl).into(holder.imgProduct)
        holder.txtProductName.text = product.productName
        holder.txtDesc.text = product.productDesc
        holder.txtStar.text = product.star.toString()
        holder.txtDistance.text = product.distance
        holder.txtPromoDesc.text = product.promoDesc
        holder.txtOutlet.text = context.resources.getString(R.string.outlet, product.outletDesc)

        if(product.isClose) {
            holder.closeOverlay.visibility = View.VISIBLE
            holder.txtCloseLabel.text = product.closeLabel
        }

        val outlet = when {
            product.outletAround != null -> context.resources.getString(R.string.outlet, product.outletAround)
            product.outletDesc != null && product.outletDesc != 0 -> context.resources.getString(R.string.outlet, product.outletDesc)
            else -> ""
        }
        holder.txtOutlet.text = outlet
        holder.listItem.setOnClickListener {
            listener.onViewClick(product)
        }
    }
}