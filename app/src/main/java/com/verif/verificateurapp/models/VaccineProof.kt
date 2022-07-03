package com.verif.verificateurapp.models


import com.google.gson.annotations.SerializedName
import java.util.*

data class VaccineProof(
    @SerializedName("address")
    val address: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("birthday")
    val birthday: Date?,
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("get_gender_display")
    val getGenderDisplay: String?,
    @SerializedName("get_user_type_display")
    val getUserTypeDisplay: String?,
    @SerializedName("phone_num")
    val phoneNum: String?,
    @SerializedName("user")
    val user: Int?,
    @SerializedName("user_type")
    val userType: String?,
    @SerializedName("carte_nationale")
    val carteNationale: String?,
    @SerializedName("passport")
    val passport: String?,
    @SerializedName("vaccine_centre")
    val vaccineCentre: Any?,
    @SerializedName("vaccines")
    val vaccines: List<ProofVaccine>?
)