package com.android.coolwinks.utils

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun AppCompatActivity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {

}

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.activity, text, duration).show()
}

fun AppCompatActivity.log(message: String, tag: String = "*AppLog*") {
    Log.d(tag, message)
}

fun Fragment.log(message: String, tag: String = "*AppLog*") {
    Log.d(tag, message)
}


