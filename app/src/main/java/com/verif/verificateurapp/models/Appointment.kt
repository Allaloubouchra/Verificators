package com.verif.verificateurapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Appointment(
    val id: Long,
    @SerializedName("appointment_date")
    val appointmentDate: Date,
    @SerializedName("num_fode")
    val numDose: Int,
    val arm: String,
    @SerializedName("get_arm_display")
    val armName: String,
    val patient: Account,
    val doctor: Account?,
    val receptionist: Account?,
    val vaccine: Vaccine,
    val status: String,
    @SerializedName("get_status_display")
    val statusDisplay: String,
    val centre: Center?
) : Serializable


data class Account(
    val user: Long,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("get_user_type_display")
    val userTypeDisplay: String,
    val birthday: Date?,
    @SerializedName("phone_num")
    val phone: String,
    val address: String,
    val age: Int,
    val gender: String,
    @SerializedName("get_gender_display")
    val genderDisplay: String,
    @SerializedName("full_name")
    val fullName: String,
) : Serializable
