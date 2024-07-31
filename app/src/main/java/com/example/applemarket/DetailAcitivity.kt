package com.example.applemarket

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailAcitivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var isLike = false

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }

    private val item: Item? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, Item::class.java)
        } else {
            intent.getParcelableExtra<Item>(Constants.ITEM_OBJECT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBind() // 데이터 바인딩
        setLike() // 아이콘 초기값 설정 및 클릭 이벤트 처리

        // 뒤로가기 눌렀을 때 데이터 넘겨주기
        binding.ivBack.setOnClickListener { exit() }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun onBind(){
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
    }

    private fun setLike(){
        isLike = item?.isLike == true

        binding.ivDetailLike.setImageResource(
            if (isLike) {
                R.drawable.ic_like_filled
            } else {
                R.drawable.ic_like_border
            }
        )

        binding.ivDetailLike.setOnClickListener {
            if (!isLike) {
                binding.ivDetailLike.setImageResource(R.drawable.ic_like_filled)
                Snackbar.make(binding.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true
            } else {
                binding.ivDetailLike.setImageResource(R.drawable.ic_like_border)
                isLike = false
            }
        }
    }

    private fun exit() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(Constants.ITEM_INDEX, itemPosition)
            putExtra(Constants.IS_LIKE, isLike)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            exit()
        }
    }
}