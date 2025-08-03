package com.appsv.revisecontactapp.presentation.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

fun ImageCompress(image: ByteArray): ByteArray {
    val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)
    return outputStream.toByteArray()

}