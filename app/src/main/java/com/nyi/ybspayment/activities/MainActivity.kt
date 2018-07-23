package com.nyi.ybspayment.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.nyi.ybspayment.R
import com.nyi.ybspayment.db.AppDatabase
import com.nyi.ybspayment.db.DBWorkerThread
import com.nyi.ybspayment.db.Transaction
import com.nyi.ybspayment.fragments.BottomSheetFragment

class MainActivity : AppCompatActivity() {
    var PERMISSIONS_CAMERA = 1;

    private lateinit var scannerView : CodeScannerView;
    private lateinit var codeScanner: CodeScanner

    private lateinit var mBottomSheetBehaviour: BottomSheetBehavior<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestPermission();

        val btnPay = findViewById<Button>(R.id.btn_pay);
        val menu = findViewById<ImageView>(R.id.iv_menu);

        //BottomSheet
        //val nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView);
        //mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView);



        btnPay.setOnClickListener {
            //scannerView.visibility = View.VISIBLE;

            val intent = Intent(this@MainActivity, ScannerActivity::class.java)
            startActivity(intent)
        }

        menu.setOnClickListener{
            // Will show the bottom sheet
            //mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
            var bottomSheetFragment = BottomSheetFragment();
            bottomSheetFragment.show(supportFragmentManager, "TAG");
        }
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

    /*
        Room database testing
     */
    private lateinit var mDbWorkerThread: DBWorkerThread
    private val mUiHandler = Handler()
    private var mDb: AppDatabase? = null

    fun insertTransactionInDb(transaction: Transaction) {
        mDbWorkerThread = DBWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        mDb = AppDatabase.getInstance(this)

        val task = Runnable { mDb?.TransactionDao()?.insert(transaction) }
        mDbWorkerThread.postTask(task)
    }
    private fun fetchTransactionDataFromDb() {
        val task = Runnable {
            val transactionData = mDb?.TransactionDao()?.getAll()
            mUiHandler.post({
                if (transactionData == null || transactionData?.size == 0) {

                } else {

                }
            })
        }
        mDbWorkerThread.postTask(task)
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

}
