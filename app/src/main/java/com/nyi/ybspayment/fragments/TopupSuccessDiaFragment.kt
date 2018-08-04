package com.nyi.ybspayment.fragments

import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.nyi.ybspayment.R
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class TopupSuccessDiaFragment : DialogFragment(){

    companion object {
        private val argRechareAmount = "argRechareAmount"

        fun newInstance(rechargeAmount : Int) : TopupSuccessDiaFragment{
            val frag = TopupSuccessDiaFragment()

            val args = Bundle()
            args.putInt(argRechareAmount, rechargeAmount)

            frag.arguments = args
            return frag
        }
    }

    var rechargeAmount : Int = 0

    lateinit var rootView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rechargeAmount = arguments.getInt(argRechareAmount)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout to use as dialog or embedded fragment
        rootView = inflater.inflate(R.layout.dialoge_fragment_topup_success, container, false)

        val tvRecharegeAmount = rootView.findViewById<TextView>(R.id.tv_topup_success_amount)
        val btnOK = rootView.findViewById<Button>(R.id.btn_dia_OK)

        tvRecharegeAmount.setText(rechargeAmount.toString())

        btnOK.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        return  rootView
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }
}