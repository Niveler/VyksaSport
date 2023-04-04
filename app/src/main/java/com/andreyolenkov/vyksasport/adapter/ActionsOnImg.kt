package com.andreyolenkov.vyksasport.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class ActionsOnImg() {

    fun getImg(byteArray: ByteArray):Bitmap{
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