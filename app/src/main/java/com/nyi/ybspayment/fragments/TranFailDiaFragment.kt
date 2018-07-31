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


class TranFailDiaFragment : DialogFragment(){

    companion object {
        private val argFee = "argFee"

        fun newInstance(fee : Int) : TranFailDiaFragment{
            val frag = TranFailDiaFragment()

            val args = Bundle()
            args.putInt(argFee, fee)

            frag.arguments = args
            return frag
        }
    }

    var fee = 0

    lateinit var rootView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fee = arguments.getInt(argFee)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout to use as dialog or embedded fragment
        rootView = inflater.inflate(R.layout.dialoge_fragment_tran_fail, container, false)

        val tvFee = rootView.findViewById<TextView>(R.id.tv_tran_fail_fee)
        val btnOK = rootView.findViewById<Button>(R.id.btn_dia_OK)

        tvFee.setText(fee.toString())

        btnOK.setOnClickListener(View.OnClickListener {
            dismiss()
        })

        return  rootView
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        return dialog
    }

    override fun onStart() {
        super.onStart()
    }
}