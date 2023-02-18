package com.danisbana.danisbanaapp.domain.usecase

class ValidatePassword {

    fun execute(password: String): ValidationResult {
        if(password.length < 6) {
            return ValidationResult(
                successful = false,
                errorMessage = "Parola en az 6 karakterden oluşmalıdır"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if(!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Parola en az bir harf ve bir rakam içermelidir"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}