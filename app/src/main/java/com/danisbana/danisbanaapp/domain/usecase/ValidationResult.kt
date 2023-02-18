package com.danisbana.danisbanaapp.domain.usecase

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)