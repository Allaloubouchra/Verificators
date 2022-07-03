package com.verif.verificateurapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.verif.verificateurapp.models.Appointment
import com.verif.verificateurapp.rest.AppointmentService
import com.verif.verificateurapp.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val getBarCode =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::handleResult
        )


    val service: AppointmentService = RetrofitClient.client.create(AppointmentService::class.java)
    var barcode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.scannerButton).setOnClickListener {
            scanQrCode()
//            triggerRequest(patientId)
        }
    }

    private fun scanQrCode() {
        getBarCode.launch(Intent(applicationContext, ScannerActivity::class.java))
    }

    private fun handleResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            if (result.data != null && result.data!!.getStringExtra("scanned_code") != null) {
                barcode = result.data!!.getStringExtra("scanned_code")!!
                if (barcode.toLongOrNull() != null) {
                    triggerRequest(barcode.toLong())
                }

            }
        }
    }

    private fun triggerRequest(id: Long) {
        service.getProof(id)
            .enqueue(object : Callback<List<Appointment>> {
                override fun onResponse(
                    call: Call<List<Appointment>>,
                    response: Response<List<Appointment>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        handleResponse(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Rendez-vous introuvable",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

    }

    private fun handleResponse(appointment: List<Appointment>) {
        //todo display in recycler view
    }
}