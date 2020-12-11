package com.nazar.sobatmasjid.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController

fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
    ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
}

fun NavController.popBackStackAllInstances(destination: Int, inclusive: Boolean): Boolean {
    var popped: Boolean
    while (true) {
        popped = popBackStack(destination, inclusive)
        if (!popped) {
            break
        }
    }
    return popped
}

fun <T> lazyN(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)