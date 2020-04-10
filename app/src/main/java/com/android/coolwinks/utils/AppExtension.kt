package com.android.coolwinks.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlin.math.roundToInt

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
// Extension function for Flickr thumbnail height
fun ImageView.resizeImage() {
    val WIDTH_SCALE_FACTOR = 0.30
    var screenWidth :Int = this.context.resources.displayMetrics.widthPixels
    val height = screenWidth * WIDTH_SCALE_FACTOR
    this.layoutParams.height= height.roundToInt()
    this.requestLayout()
}



