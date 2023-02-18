package com.danisbana.danisbanaapp.core.model.profile

import com.google.firebase.auth.FirebaseUser

class AppUser (
    var firebaseUser: FirebaseUser? = null,
    val info: UserInfo? = UserInfo()
)