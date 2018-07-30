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


class TranSuccessDiaFragment : DialogFragment(){

    companion object {
        private val argFee = "argFee"
        private val argBusNo = "argBusNo"
        private val argBusLine = "argBusLine"

        fun newInstance(fee : Int, busNo : String, busLine : String) : TranSuccessDiaFragment{
            val frag = TranSuccessDiaFragment()

            val args = Bundle()
            args.putInt(argFee, fee)
            args.putString(argBusNo, busNo)
            args.putString(argBusLine, busLine)

            frag.arguments = args
            return frag
        }
    }

    var fee = 0
    lateinit var busLine : String
    lateinit var busNo : String

    lateinit var rootView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fee = arguments.getInt(argFee)
        busLine = arguments.getString(argBusLine)
        busNo = arguments.getString(argBusNo)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout to use as dialog or embedded fragment
        rootView = inflater.inflate(R.layout.dialoge_fragment_tran_success, container, false)

        val tvFee = rootView.findViewById<TextView>(R.id.tv_tran_success_fee)
        val tvBusNo = rootView.findViewById<TextView>(R.id.tv_tran_success_bus_no)
        val tvBusLine = rootView.findViewById<TextView>(R.id.tv_tran_success_bus_line)
        val btnOK = rootView.findViewById<Button>(R.id.btn_dia_OK)

        tvFee.setText(fee.toString())
        tvBusNo.setText(busNo)
        tvBusLine.setText(busLine)

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