package com.example.iv_test_hazirah.ui.details

import com.example.iv_test_hazirah.data.model.Product

class DetailsPresenter(private val view: DetailsView) {
    fun getOutlet(product: Product): String {
        return when {
            product.outletAround != null -> view.getStringValue(product.outletAround)
            product.outletDesc != null && product.outletDesc != 0 -> view.getStringValue(product.outletDesc)
            else -> ""
        }
    }

    fun onOptionItemSelected(itemId: Int): Boolean {
        return when (itemId) {
            android.R.id.home -> {
                view.goBack()
                true
            }
            else -> false
        }
    }
}