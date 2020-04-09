package com.android.coolwinks.utils

import org.joda.time.*

object Util {
    fun calculateAge(publishedAt: String?): String {
        val age = ""
        publishedAt?.let {
            val dv: Long = java.lang.Long.valueOf(it) * 1000
            val dt1 = Instant(dv)
            val dt2 = Instant(System.currentTimeMillis())
            var age = ""
            val thours = Hours.hoursBetween(dt1, dt2).hours
            val tminutes = Minutes.minutesBetween(dt1, dt2).minutes
            val tseconds = Seconds.secondsBetween(dt1, dt2).seconds
            val tdays = Days.daysBetween(dt1, dt2).days

            val hours = thours % 24
            val minutes = tminutes % 60
            val seconds = tseconds % 60

            if (tdays > 0) {
                age = if (tdays === 1) {
                    "$tdays day ago"
                } else {
                    "$tdays days ago"
                }
                return age
            } else if (hours > 0) {
                age = if (hours === 1) {
                    "$hours hour ago"
                } else {
                    "$hours hours ago"
                }
                return age
            } else if (minutes > 0) {
                age = if (minutes === 1) {
                    "$minutes minute ago"
                } else {
                    "$minutes minutes ago"
                }
                return age
            } else if (seconds > 0) {
                return "Just Now"

            }
        }
        return age
    }
}