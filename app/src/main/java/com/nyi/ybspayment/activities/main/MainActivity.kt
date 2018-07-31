package com.nyi.ybspayment.activities.main

import android.Manifest
import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.nyi.ybspayment.R
import com.nyi.ybspayment.activities.scanner.ScannerActivity
import com.nyi.ybspayment.activities.transactionHistory.TransactionHistory
import com.nyi.ybspayment.db.model.UserModel
import com.nyi.ybspayment.fragments.BottomSheetFragment
import com.nyi.ybspayment.fragments.TranFailDiaFragment
import com.nyi.ybspayment.fragments.TranSuccessDiaFragment
import com.nyi.ybspayment.utils.Constants
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), MainContract.MainView {

    var PERMISSIONS_CAMERA = 1;

    private lateinit var scannerView : CodeScannerView;
    private lateinit var codeScanner: CodeScanner
    private lateinit var mainPresenter: MainPresenter

    private lateinit var mBottomSheetBehaviour: BottomSheetBehavior<*>

    //View
    private lateinit var btnPay : Button
    private lateinit var menu : ImageView
    private lateinit var tvUserId : TextView
    private lateinit var tvTopUp : TextView
    private lateinit var tvHistory : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        btnPay = findViewById<Button>(R.id.btn_pay);
        menu = findViewById<ImageView>(R.id.iv_menu);
        tvUserId = findViewById<TextView>(R.id.tv_user_id)
        tvTopUp = findViewById<TextView>(R.id.tv_main_topup)
        tvHistory = findViewById<TextView>(R.id.tv_main_history)

        mainPresenter = MainPresenter(this)
        mainPresenter.init()

        requestPermission();

        //BottomSheet
        //val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView);
        //mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView);

        btnPay.setOnClickListener {
            mainPresenter.clickPay()
        }

        menu.setOnClickListener{
            // Will show the bottom sheet
            //mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
            var bottomSheetFragment = BottomSheetFragment();
            bottomSheetFragment.show(supportFragmentManager, "TAG");
        }

        tvHistory.setOnClickListener {
            //Toast.makeText(applicationContext, "Clcik History", Toast.LENGTH_SHORT).show()
            mainPresenter.clickHistory()
        }
    }

    override fun updateAvailAmount(availAmount: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showUserInfo(user: UserModel) {
        tvUserId.setText(user.userID)
        tv_remaining_balance.setText(user.availableAMount.toString())
    }

    override fun goScannerActivity() {
        //scannerView.visibility = View.VISIBLE;
        val intent = Intent(this@MainActivity, ScannerActivity::class.java)
        //startActivity(intent)
        startActivityForResult(intent, Constants.RequestToScannerActivityCode)
    }

    override fun goTransactionHistoryActivity() {
        val intent = Intent(this@MainActivity, TransactionHistory::class.java)
        startActivity(intent)
    }

    override fun goTopUpActivity() {

    }

    override fun successDiaView(fee : Int, busNo : String, busLine : String) {
        //Toast.makeText(applicationContext, "Success ", Toast.LENGTH_SHORT).show()
        val successDiaFragment : DialogFragment = TranSuccessDiaFragment.newInstance(fee, busNo, busLine)
        successDiaFragment.isCancelable = false
        successDiaFragment.show(fragmentManager, "Success")

    }

    override fun notEnoughBalanceDiaView(fee : Int) {
        val failDialog : DialogFragment = TranFailDiaFragment.newInstance(fee)
        failDialog.isCancelable = false
        failDialog.show(fragmentManager, "fail")
    }

    fun requestPermission(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        PERMISSIONS_CAMERA)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constants.RequestToScannerActivityCode){
            mainPresenter.resultFromScannerActivity(resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.init()
    }
}
