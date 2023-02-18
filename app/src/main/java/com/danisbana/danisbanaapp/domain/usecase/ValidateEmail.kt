package com.danisbana.danisbanaapp.domain.usecase

import android.util.Patterns

class ValidateEmail {

    fun execute(email: String): ValidationResult {
        if(email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "The email can't be blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Hatalı veya eksik e-posta adresi girdiniz"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}