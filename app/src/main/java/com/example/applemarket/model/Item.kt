package com.android.customitemview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val Image: Int,
    val ItemTitle: String,
    val ItemExplain: String,
    val SellerName: String,
    val Price: Int,
    val Address: String,
    var LikeCnt: Int,
    val CommentCnt: Int,
)  : Parcelable