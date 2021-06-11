package com.example.e4.models

import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object Connection {

    fun post_color(color: Int) {
        try {
            for (device in Data.devices){
                if (device.state!!){
                    var url = URL("http://${device.ip}/")
                    val conn = url.openConnection() as HttpURLConnection
                    val urlParameters = "r=${color.red}&g=${color.green}&b=${color.blue}"
                    val postData: ByteArray = urlParameters.toByteArray(StandardCharsets.UTF_8)

                    conn.requestMethod = "POST"
                    conn.doOutput = true;
                    conn.instanceFollowRedirects = false
                    DataOutputStream(conn.outputStream).use { wr -> wr.write(postData) }
                }
            }

        } catch (e: Exception){
            e.printStackTrace()
        }


    }


}