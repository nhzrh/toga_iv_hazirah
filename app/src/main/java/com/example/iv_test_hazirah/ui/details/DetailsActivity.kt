package com.example.iv_test_hazirah.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.iv_test_hazirah.R
import com.example.iv_test_hazirah.data.model.Product
import com.example.iv_test_hazirah.traits.StartActivity
import com.example.iv_test_hazirah.ui.home.HomeActivity.Companion.PRODUCT
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*


class DetailsActivity : AppCompatActivity(), DetailsView {

    companion object : StartActivity

    private lateinit var presenter: DetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        presenter = DetailsPresenter(this)
        val product = intent!!.getSerializableExtra(PRODUCT) as Product

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = product.productName

        Picasso.with(this).load(product.imageUrl).into(imgProduct)
        txtDesc.text = product.productDesc
        txtStar.text = product.star.toString()
        txtDistance.text = product.distance
        txtPromoDesc.text = product.promoDesc
        txtOutlet.text = presenter.getOutlet(product)
    }

    override fun getStringValue(text: Int): String {
       return getString(R.string.outlet, text)
    }

    override fun goBack() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.onOptionItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}