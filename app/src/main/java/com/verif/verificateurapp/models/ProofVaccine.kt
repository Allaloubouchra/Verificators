package com.verif.verificateurapp.models


import com.google.gson.annotations.SerializedName

data class ProofVaccine(
    @SerializedName("doses")
    val doses: Int?,
    @SerializedName("vaccine__name")
    val vaccineName: String?,
    @SerializedName("vaccine__required_doses")
    val vaccineRequiredDoses: Int?,
    @SerializedName("valid")
    val valid: Boolean?
)