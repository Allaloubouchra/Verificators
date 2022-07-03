package com.verif.verificateurapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private val TAG = ScannerActivity::class.simpleName

    private lateinit var mScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
        mScannerView.setAutoFocus(true)
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView.startCamera() // Start camera on resume
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera() // Stop camera on pause
    }

    override fun handleResult(rawResult: Result?) {
        Log.d(TAG, "Scanned: ${rawResult?.text}")
        val data = Intent()
        data.putExtra("scanned_code", rawResult?.text)
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Scanner Annulé", Toast.LENGTH_SHORT).show()
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }
}