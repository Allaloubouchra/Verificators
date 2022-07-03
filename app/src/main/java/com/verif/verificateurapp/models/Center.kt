package com.verif.verificateurapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Center(
    val id: Long,
    val name: String,
    val address: String,
    @SerializedName("num_phone")
    val phone: String,
    val latitude: Double,
    val longitude: Double,
    val city: City,
    @SerializedName("working_hours")
    val workingHours: Array<WorkingHour>,
    val vaccines: Array<ProofVaccine>,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Center

        if (id != other.id) return false
        if (name != other.name) return false
        if (address != other.address) return false
        if (phone != other.phone) return false
        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (city != other.city) return false
        if (!workingHours.contentEquals(other.workingHours)) return false
        if (!vaccines.contentEquals(other.vaccines)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + phone.hashCode()
        result = 31 * result + latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + city.hashCode()
        result = 31 * result + workingHours.contentHashCode()
        result = 31 * result + vaccines.contentHashCode()
        return result
    }
}

data class City(val id: Long, val name: String, val matricule: Long, val wilaya: Long) :
    Serializable


data class WorkingHour(
    val id: Long,
    val center: Long,
    @SerializedName("day_of_week")
    val dayOfWeek: Long,
    @SerializedName("from_hour")
    val fromHour: String,
    @SerializedName("to_hour")
    val toHour: String,
    @SerializedName("from_hour_s")
    val fromHourS: String,
    @SerializedName("to_hour_s")
    val toHourS: String,
    @SerializedName("get_day_of_week_display")
    val dayOfWeekStr: String,
) : Serializable

data class  Vaccine(
    val center: Long,
    val vaccine: Long,
    val available: Boolean,
    @SerializedName("vaccine_name") val vaccineName: String?,
    val name:String?,
) : Serializable