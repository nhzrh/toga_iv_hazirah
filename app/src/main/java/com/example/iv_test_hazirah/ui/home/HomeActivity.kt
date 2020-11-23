package com.example.iv_test_hazirah.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iv_test_hazirah.R
import com.example.iv_test_hazirah.data.model.Product
import com.example.iv_test_hazirah.data.service.ProductService
import com.example.iv_test_hazirah.ui.details.DetailsActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity(), HomeView{

    private lateinit var presenter: HomePresenter
    override lateinit var homeAdapter: HomeAdapter

    companion object {
        const val PRODUCT = "PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = HomePresenter(this, ProductService())
        presenter.getProduct()
    }

    override fun initAdapter(list: Array<Product>) {
        homeAdapter = HomeAdapter(list, this, object : HomeAdapter.OnViewClickListener {
            override fun onViewClick(data: Product) {
                changeViewDetails(data)
            }
        })
        listProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }

    fun changeViewDetails(product: Product) {
        DetailsActivity.start(this@HomeActivity, Bundle().apply {
            putSerializable(PRODUCT, product)
        })
    }

    override fun showSnackBar(text: String) {
        Snackbar.make(home, text, Snackbar.LENGTH_LONG).show()
    }
}