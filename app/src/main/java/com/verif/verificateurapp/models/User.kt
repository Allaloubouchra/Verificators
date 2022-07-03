package com.verif.verificateurapp.models

data class User(
    val token: String,
    val id: Int,
    val username: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val user_type: String,
)