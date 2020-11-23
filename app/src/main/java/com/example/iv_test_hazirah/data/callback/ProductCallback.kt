package com.example.iv_test_hazirah.data.callback

import androidx.annotation.NonNull
import com.example.iv_test_hazirah.data.model.Product

interface ProductCallback {
    fun onSuccess(list: Array<Product>?)
    fun onError(@NonNull value: String)
}