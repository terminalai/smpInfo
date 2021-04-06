package com.thepyprogrammer.gaitanalyzer.model.python

import android.content.Intent

interface IPythonApp {
    fun onCreate()
    fun onResume()
    fun onStart()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
