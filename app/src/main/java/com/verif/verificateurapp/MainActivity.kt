package com.verif.verificateurapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.verif.verificateurapp.models.VaccineProof
import com.verif.verificateurapp.rest.AppointmentService
import com.verif.verificateurapp.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var lastName: TextView

    private lateinit var birthDay: TextView
    private lateinit var numCarteIdentite: TextView
    private lateinit var passportNumber: TextView
    private lateinit var vaccinationProofs: RecyclerView

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
        requestPermissions(arrayOf(Manifest.permission.CAMERA), 102)
        lastName = findViewById(R.id.lastName)

        birthDay = findViewById(R.id.birthDay)
        numCarteIdentite = findViewById(R.id.NumCarteIdentite)
        passportNumber = findViewById(R.id.passportNumber)
        vaccinationProofs = findViewById(R.id.vaccinationProofs)

        findViewById<Button>(R.id.scannerButton).setOnClickListener {
//            scanQrCode()
            triggerRequest(2)
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
            .enqueue(object : Callback<VaccineProof> {
                override fun onResponse(
                    call: Call<VaccineProof>,
                    response: Response<VaccineProof>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        handleResponse(response.body()!!)
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Patient Introuvable",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<VaccineProof>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Patient Introuvable",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })

    }

    @SuppressLint("SetTextI18n")
    private fun handleResponse(vaccineProof: VaccineProof) {
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        lastName.text = "Nom: ${vaccineProof.fullName}"
        birthDay.text = "Date de naissance: ${vaccineProof.birthday?.let { formatter.format(it) }}"
        numCarteIdentite.text = "Numéro de la carte d'identité: ${vaccineProof.carteNationale}"
        passportNumber.text = "Numéro de passeport: ${vaccineProof.passport}"
        vaccinationProofs.adapter = AdapterListAppointment(vaccineProof.vaccines!!)
    }
}