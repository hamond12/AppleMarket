package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ItemBinding
import java.text.DecimalFormat

class ItemAdapter(private val mItems: MutableList<Item>) : RecyclerView.Adapter<ItemAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    interface ItemLongClick {
        fun onLongClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }

        holder.itemView.setOnLongClickListener() OnLongClickListener@{
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }

        holder.ivItemImg.setImageResource(mItems[position].Image)
        holder.tvItemTitle.text = mItems[position].ItemTitle
        holder.tvAddress.text = mItems[position].Address

        val price = mItems[position].Price
        holder.tvPrice.text = DecimalFormat("#,###").format(price)+"원"

        holder.tvCommentCnt.text = mItems[position].CommentCnt.toString()
        holder.tvLikeCnt.text = mItems[position].LikeCnt.toString()

        if(mItems[position].isLike) holder.ivLike.setImageResource(R.drawable.ic_like_filled)
        else holder.ivLike.setImageResource(R.drawable.ic_like_border)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivItemImg = binding.ivItemImg
        val tvItemTitle = binding.tvItemTitle
        val tvAddress = binding.tvAddress
        val tvPrice = binding.tvPrice
        val tvCommentCnt = binding.tvCommentCnt
        val tvLikeCnt = binding.tvLikeCnt
        val ivLike = binding.ivLike
    }
}