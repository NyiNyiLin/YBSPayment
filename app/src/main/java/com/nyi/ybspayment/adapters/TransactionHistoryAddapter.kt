package com.nyi.ybspayment.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nyi.ybspayment.R
import com.nyi.ybspayment.VH.TransactionHistoryVH
import com.nyi.ybspayment.db.model.TransactionModel

class TransactionHistoryAddapter(val transactionList : MutableList<TransactionModel> = arrayListOf()) : RecyclerView.Adapter<TransactionHistoryVH>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TransactionHistoryVH {
        val v = LayoutInflater.from(parent!!.context).inflate(R.layout.item_view_transaction_history, parent, false)

        return TransactionHistoryVH(v)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: TransactionHistoryVH?, position: Int) {
        holder!!.bindVH(transactionList.get(position))
    }
}