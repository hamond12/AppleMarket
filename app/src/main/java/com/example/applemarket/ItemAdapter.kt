package com.android.customitemview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.customitemview.model.Item
import com.example.applemarket.databinding.ItemBinding
import java.text.DecimalFormat

class ItemAdapter(private val mItems: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemImageView.setImageResource(mItems[position].Image)
        holder.tvItemTitle.text = mItems[position].ItemTitle
        holder.tvAddress.text = mItems[position].Address

        val price = mItems[position].Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price)+"원"

        holder.tvItemComment.text = mItems[position].CommentCnt.toString()
        holder.tvItemLike.text = mItems[position].LikeCnt.toString()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImageView = binding.ivItem
        val tvItemTitle = binding.tvItemTitle
        val tvAddress = binding.tvAddress
        val tvPrice = binding.tvPrice
        val tvItemComment = binding.tvCommentCnt
        val tvItemLike = binding.tvLikeCnt
    }
}