package com.nyi.ybspayment.VH

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.nyi.ybspayment.R
import com.nyi.ybspayment.db.model.TransactionModel
import com.nyi.ybspayment.utils.TimeUtil

class TransactionHistoryVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    lateinit var tvDate : TextView
    lateinit var tvTime : TextView
    lateinit var tvBusLine : TextView
    lateinit var tvCarNo : TextView
    lateinit var tvFee : TextView

    init {
        tvDate = itemView!!.findViewById<TextView>(R.id.tv_item_tran_hist_date)
        tvTime = itemView!!.findViewById<TextView>(R.id.tv_item_tran_hist_time)
        tvBusLine = itemView!!.findViewById<TextView>(R.id.tv_item_tran_hist_busLine)
        tvCarNo = itemView!!.findViewById<TextView>(R.id.tv_item_tran_hist_car_no)
        tvFee = itemView!!.findViewById<TextView>(R.id.tv_item_tran_hist_fee)
    }

    fun bindVH(transaction : TransactionModel){
        tvDate.setText(TimeUtil.getDateFromString(transaction.time))
        tvTime.setText(TimeUtil.getTimeFromString(transaction.time))
        tvFee.setText(transaction.fee.toString())
        tvBusLine.setText(transaction.busLine)
        tvCarNo.setText(transaction.carNo)
    }
}

