package com.nyi.ybspayment.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nyi.ybspayment.R
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.adapters.TopupHistoryAddapter
import com.nyi.ybspayment.adapters.TransactionHistoryAddapter
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.TopupModel
import com.nyi.ybspayment.db.model.TransactionModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopupHistoryFragment : Fragment() {

    lateinit var rvList : RecyclerView

    lateinit var historyAdapter : TopupHistoryAddapter

    lateinit var dbHelper: DBHelper

    var topupList : MutableList<TopupModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHelper = DBHelper(YbsPayment.context)


        topupList = dbHelper.readAllTopup()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_topup_history, container, false)

        rvList = v.findViewById<RecyclerView>(R.id.rv_topup_history)
        historyAdapter = TopupHistoryAddapter(topupList)
        rvList.setLayoutManager(LinearLayoutManager(context))
        rvList.setAdapter(historyAdapter)



        return v
    }


}
