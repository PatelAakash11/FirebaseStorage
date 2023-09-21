package com.example.firebasestorageexample.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast

fun Activity.openActivity(targetAct: Class<*>) {
    startActivity(Intent(this, targetAct))
}

fun Activity.showToast(message: Any) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
}

fun showLog(tag: String? = "TAG_APP", message: Any) {
    Log.d(tag, "showLog: $message")
}

//fun Activity.loadCursor(query: Uri? = MediaStore.Images.Media.EXTERNAL_CONTENT_URI) {
//    val cursor = contentResolver.query(query!!, null, null, null, null)
//
//    val listOfItem = arrayListOf<Any>()
//    if (cursor != null) {
//        if (cursor.moveToFirst()) {
//            do {
//
//            } while (cursor.moveToNext())
//        }
//    }
//}