package com.example.iv_test_hazirah.ui.home

import com.example.iv_test_hazirah.data.callback.ProductCallback
import com.example.iv_test_hazirah.data.model.Product
import com.example.iv_test_hazirah.data.service.ProductService

class HomePresenter(private val view: HomeView, private val service: ProductService) {
    fun getProduct() {
        service.getProduct(object : ProductCallback {
            override fun onSuccess(list: Array<Product>?) {
                if(list != null) {
                    view.initAdapter(list)
                }
            }

            override fun onError(value: String) {
                view.showSnackBar(value)
            }
        })
    }
}