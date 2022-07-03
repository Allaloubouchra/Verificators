package com.verif.verificateurapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.verif.verificateurapp.models.ProofVaccine

class AdapterListAppointment(private val proofsList: List<ProofVaccine>) :
    RecyclerView.Adapter<AdapterListAppointment.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vaccinatin_proof_element, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vaccine.text = "Vaccin: ${proofsList[position].vaccineName}"
        holder.dose.text = "Doses: ${proofsList[position].doses}"
        holder.valid.text = "Valid: ${if (proofsList[position].valid == true) "Oui" else "Non"}"
    }

    override fun getItemCount(): Int {
        return proofsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vaccine: TextView
        val dose: TextView
        val valid: TextView


        init {
            vaccine = itemView.findViewById(R.id.vaccine)
            dose = itemView.findViewById(R.id.dose)
            valid = itemView.findViewById(R.id.valid)
        }
    }
}