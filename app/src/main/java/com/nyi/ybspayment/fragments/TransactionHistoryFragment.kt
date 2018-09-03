package com.nyi.ybspayment.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nyi.ybspayment.R
import com.nyi.ybspayment.YbsPayment
import com.nyi.ybspayment.adapters.TransactionHistoryAddapter
import com.nyi.ybspayment.db.DBHelper
import com.nyi.ybspayment.db.model.TransactionModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransactionHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class TransactionHistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var rvList : RecyclerView

    lateinit var historyAdapter : TransactionHistoryAddapter

    lateinit var dbHelper: DBHelper

    var transactionList : MutableList<TransactionModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/

        dbHelper = DBHelper(YbsPayment.context)

        transactionList = dbHelper.readAllTransaction()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_transaction_history, container, false)

        rvList = view.findViewById<RecyclerView>(R.id.rv_transaction_history)
        historyAdapter = TransactionHistoryAddapter(transactionList)
        rvList.setLayoutManager(LinearLayoutManager(context))
        rvList.setAdapter(historyAdapter)

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TransactionHistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        /*@JvmStatic
        fun newInstance(param1: String, param2: String) =
                TransactionHistoryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }*/
    }
}
