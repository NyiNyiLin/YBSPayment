package com.nyi.ybspayment.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nyi.ybspayment.R
import com.nyi.ybspayment.VH.TopupHistoryVH
import com.nyi.ybspayment.VH.TransactionHistoryVH
import com.nyi.ybspayment.db.model.TopupModel
import com.nyi.ybspayment.db.model.TransactionModel

class TopupHistoryAddapter(val topupList : MutableList<TopupModel> = arrayListOf()) : RecyclerView.Adapter<TopupHistoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TopupHistoryVH {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.item_view_topup_history, parent, false)

        return TopupHistoryVH(v)
    }

    override fun getItemCount(): Int {
        return topupList.size
    }

    override fun onBindViewHolder(holder: TopupHistoryVH?, position: Int) {
        holder!!.bindVH(topupList.get(position))
    }
}