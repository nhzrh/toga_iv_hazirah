package com.example.iv_test_hazirah.data.api

import com.example.iv_test_hazirah.data.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {
    @GET("get")
    fun getProduct(): Call<Array<Product>>
}