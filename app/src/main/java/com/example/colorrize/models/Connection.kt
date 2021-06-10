package com.example.colorrize.models

import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

object Connection {

    fun post_color(color: Int, i: Int? = null) {

        if (i == null){
            for (index in Data.devices.indices){
                if (Data.devices[index].state){
                    this.post_color(color, index)
                }
            }
        }
        else{
            var url = URL("http://${Data.devices[i].ip}/")
            val conn = url.openConnection() as HttpURLConnection
            val urlParameters = "r=${color.red}&g=${color.green}&b=${color.blue}"
            val postData: ByteArray = urlParameters.toByteArray(StandardCharsets.UTF_8)

            conn.requestMethod = "POST"
            conn.doOutput = true;
            conn.instanceFollowRedirects = false
            DataOutputStream(conn.outputStream).use { wr -> wr.write(postData) }
        }


    }

}