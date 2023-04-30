package com.rajit.activitylifecycleexample.util

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object UiUtils {

    fun AppCompatActivity.showShortToast(
        mContext: Context,
        msg: String
    ) {
        Toast.makeText(
            mContext,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun AppCompatActivity.showLongToast(
        mContext: Context,
        msg: String
    ) {
        Toast.makeText(
            mContext,
            msg,
            Toast.LENGTH_LONG
        ).show()
    }

}