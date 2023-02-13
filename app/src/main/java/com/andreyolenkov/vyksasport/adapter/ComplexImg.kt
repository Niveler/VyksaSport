package com.andreyolenkov.vyksasport.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ComposePathEffect
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ComplexImg() {

    fun getComplexImg(byteArray: ByteArray):Bitmap{
        try {
            return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
        }catch (exc:Exception) {
            throw Exception(exc.message)
        }
    }
    fun writeImg(bitmap: Bitmap):ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val imgArray = baos.toByteArray()
        return  imgArray
    }
}