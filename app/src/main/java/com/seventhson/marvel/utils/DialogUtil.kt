package com.seventhson.marvel.utils

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.seventhson.marvel.R

class DialogUtil {

    fun getLoadingDialog(
        activity: Activity
    ) : Dialog {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.loading_layout)
        return dialog
    }

}