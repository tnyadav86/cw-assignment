package com.android.coolwinks

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected lateinit var appCompatActivity: AppCompatActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is AppCompatActivity) {
            appCompatActivity = context
        }

    }

}
