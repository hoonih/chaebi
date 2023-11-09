package com.example.chaebi.util

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

class PopUpUtil(context: Context) {

    fun snackbar(v: View, s: String) {
        Snackbar.make(
            v, s, Snackbar.LENGTH_SHORT
        ).show()
    }
}