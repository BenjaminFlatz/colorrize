package com.example.colorrize.models

import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class Connection {

    fun post_color(color: Int, i: Int) {
        var url = URL("http://${Devices.deviceIP[i]}/")
        val conn = url.openConnection() as HttpURLConnection
        val urlParameters = "r=${color.red}&g=${color.green}&b=${color.blue}"
        val postData: ByteArray = urlParameters.toByteArray(StandardCharsets.UTF_8)

        conn.requestMethod = "POST"
        conn.doOutput = true;
        conn.instanceFollowRedirects = false
        DataOutputStream(conn.outputStream).use { wr -> wr.write(postData) }

    }

}