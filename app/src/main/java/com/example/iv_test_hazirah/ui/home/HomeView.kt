package com.example.iv_test_hazirah.ui.home

import com.example.iv_test_hazirah.data.model.Product

interface HomeView {
    var homeAdapter: HomeAdapter
    fun initAdapter(list: Array<Product>)
    fun showSnackBar(text: String)
}