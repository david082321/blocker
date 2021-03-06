package com.merxury.blocker.base

import android.content.Context

interface BasePresenter {
    fun requestPermissions()
    fun onPermissionsResult()
    fun start(context: Context)
    fun destroy()
}