package com.danisbana.danisbanaapp.core.model.register

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)