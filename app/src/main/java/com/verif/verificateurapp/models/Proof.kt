package com.verif.verificateurapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Proof(
    val user: Long,
    val user_type: String,
    val phone_num: String,
    @SerializedName("get_user_type_display")
    val get_user_type_display: String,
    val birthday: String,
    val address: String,
    val age: Long,
    val gender: String,
    @SerializedName("get_gender_display")
    val get_gender_display: String,
    val vaccine_centre: String,
    val full_name: String,
    val vaccines: List<Vaccine>
) : Serializable
