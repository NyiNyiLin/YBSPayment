package com.nyi.ybspayment.VH

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.nyi.ybspayment.R
import com.nyi.ybspayment.db.model.TopupModel
import com.nyi.ybspayment.db.model.TransactionModel
import com.nyi.ybspayment.utils.TimeUtil

class TopupHistoryVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    lateinit var tvDate : TextView
    lateinit var tvTime : TextView
    lateinit var tvAmount : TextView

    init {
        tvDate = itemView!!.findViewById<TextView>(R.id.tv_item_topup_hist_date)
        tvTime = itemView!!.findViewById<TextView>(R.id.tv_item_topup_hist_time)
        tvAmount = itemView!!.findViewById<TextView>(R.id.tv_item_topup_hist_amount)
    }

    fun bindVH(topup : TopupModel){
        tvDate.setText(TimeUtil.getDateFromString(topup.topupDate))
        tvTime.setText(TimeUtil.getTimeFromString(topup.topupDate))
        tvAmount.setText(topup.rechargeAmount.toString())
    }
}

