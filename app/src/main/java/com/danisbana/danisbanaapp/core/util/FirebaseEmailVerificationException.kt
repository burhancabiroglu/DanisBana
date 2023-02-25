package com.danisbana.danisbanaapp.core.util

import com.google.firebase.FirebaseException

class FirebaseEmailVerificationException(override val message: String = ""): FirebaseException(message) {
}

