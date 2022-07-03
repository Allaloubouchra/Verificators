package com.verif.verificateurapp.rest

import com.verif.verificateurapp.models.Appointment
import retrofit2.Call
import retrofit2.http.*

interface AppointmentService {


    @FormUrlEncoded
    @POST("vaccination-appointment/proofs/")
    fun getProof(@Field("id") id: Long): Call<List<Appointment>>

}