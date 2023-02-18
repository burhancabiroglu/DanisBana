package com.danisbana.danisbanaapp.domain.usecase

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if(password != repeatedPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Parolalar eşleşmiyor"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}