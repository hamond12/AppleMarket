package com.example.applemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.customitemview.ItemAdapter
import com.android.customitemview.model.Item
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val dataList = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList.add(Item(R.drawable.item1, "산진 한달된 선풍기 팝니다","이사가서 필요가 없어졌어요 급하게 내놓습니다", "대현동",1000, "서울 서대문구 창천동", 13, 25))
        dataList.add(Item(R.drawable.item2, "김치냉장고", "이사로인해 내놔요","안마담", 20000, "인천 계양구 귤현동", 8, 28))
        dataList.add(Item(R.drawable.item3, "샤넬 카드지갑", "고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다","코코유", 10000, "수성구 범어동", 23, 5))
        dataList.add(Item(R.drawable.item4, "금고", "금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다","Nicole", 10000, "해운대구 우제2동", 14, 17))
        dataList.add(Item(R.drawable.item5, "갤럭시Z플립3 팝니다", "갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!","절명", 150000, "연제구 연산제8동", 22, 9))

        val adapter = ItemAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : ItemAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, DetailAcitivity::class.java)
                intent.putExtra("item_index", position);
                intent.putExtra("item_object", dataList[position])
                startActivity(intent)
            }
        }

    }
}