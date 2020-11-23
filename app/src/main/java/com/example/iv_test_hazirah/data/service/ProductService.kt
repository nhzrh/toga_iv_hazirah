package com.example.iv_test_hazirah.data.service

import com.example.iv_test_hazirah.data.api.ApiClient
import com.example.iv_test_hazirah.data.api.ProductApi
import com.example.iv_test_hazirah.data.callback.ProductCallback
import com.example.iv_test_hazirah.data.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {
    private var productApi: ProductApi = ApiClient.createApi().create(ProductApi::class.java)

    fun getProduct(callback: ProductCallback) {
        val call = productApi.getProduct()
        call.enqueue(object : Callback<Array<Product>> {
            override fun onFailure(call: Call<Array<Product>>, t: Throwable) {
                callback.onError(t.message!!)
            }

            override fun onResponse(call: Call<Array<Product>>, response: Response<Array<Product>>) {
                when {
                    response.isSuccessful -> {
                        callback.onSuccess(response.body())
                    }
                    else -> {
                        callback.onError("Something went wrong with the server. Please try again after some time")
                    }
                }
            }
        })
    }
}