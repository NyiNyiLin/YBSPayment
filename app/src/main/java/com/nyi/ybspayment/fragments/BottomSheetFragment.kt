package com.nyi.ybspayment.fragments


import android.app.Dialog
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.LayoutInflater
import android.view.View
import com.nyi.ybspayment.R



/**
 * Created by IN-3442 on 22-Jul-18.
 */
class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun setupDialog(dialog: Dialog?, style: Int) {
        //Set the custom view
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_bottom_sheet, null)
        dialog?.setContentView(view)

        val params = (view.getParent() as View).layoutParams as CoordinatorLayout.LayoutParams

        (view.getParent() as View).setBackgroundResource(R.drawable.bg_bottom_sheet);

        //val behavior = params.behavior
    }
}