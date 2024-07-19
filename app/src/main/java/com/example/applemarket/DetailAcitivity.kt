package com.example.applemarket

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.android.customitemview.model.Item
import com.example.applemarket.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailAcitivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val item: Item? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item_object", Item::class.java)
        } else {
            intent.getParcelableExtra<Item>("item_object")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivItemImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                null
            )
        })

        binding.tvSellerName.text = item?.SellerName
        binding.tvSellerAddress.text = item?.Address
        binding.tvItemTitle.text = item?.ItemTitle
        binding.tvItemExplain.text = item?.ItemExplain
        binding.tvItemPrice.text = DecimalFormat("#,###").format(item?.Price) + "원"

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}