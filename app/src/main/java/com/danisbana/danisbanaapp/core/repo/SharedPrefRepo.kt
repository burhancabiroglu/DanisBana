package com.danisbana.danisbanaapp.core.repo

import android.content.Context
import android.content.SharedPreferences
import com.danisbana.danisbanaapp.application.Constants

class SharedPrefRepo constructor(private var context: Context) {
    private val credentialsPreferences: SharedPreferences
        get() = context.getSharedPreferences(
            Constants.SharedPref.APP_CREDENTIALS,
            Context.MODE_PRIVATE
        )

    private val credentialsPreferencesEditor: SharedPreferences.Editor
        get() = credentialsPreferences.edit()


    var fcmToken: String?
        get() = credentialsPreferences.getString(Constants.SharedPref.fcmToken, "")
        set(fcmToken) {
            val editor =
                credentialsPreferencesEditor.putString(
                    Constants.SharedPref.fcmToken,
                    fcmToken
                )
            editor.commit()
        }
}