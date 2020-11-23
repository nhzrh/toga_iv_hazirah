package com.example.iv_test_hazirah.data.model

import java.io.Serializable

data class Product (
    val id: Int,
    val imageUrl: String,
    val isClose: Boolean,
    val closeLabel: String,
    val productName: String,
    val productDesc: String,
    val star: Float,
    val distance: String,
    val promoDesc: String,
    val outletAround: Int? = null,
    val outletDesc: Int? = null
) : Serializable {
    override fun toString(): String {
        return "Product(id=$id, imageUrl=$imageUrl, isClose=$isClose, closeLabel=$closeLabel, productName=$productName, productDesc=$productDesc, star=$star, distance=$distance, promoDesc=$promoDesc, outletDesc=$outletDesc)"
    }
}