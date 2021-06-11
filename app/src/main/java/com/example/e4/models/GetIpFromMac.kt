package com.example.e4.models


import android.util.Log
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class getIpFromMac {


    //val macAdress = "5caafd1b0019"
    //val dataUrl = "http://api.macvendors.com/$macAdress"
    var connection: HttpURLConnection? = null

    fun get(macAddress: String, dataUrl: String){
        var dataUrl = "$dataUrl$macAddress"

        try {
            val url = URL(dataUrl)
            connection = url.openConnection() as HttpURLConnection
            connection!!.requestMethod = "POST"
            connection!!.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            connection!!.doInput = true
            connection!!.doOutput = true
            val wr = DataOutputStream(connection!!.outputStream)
            wr.flush()
            wr.close()
            val `is` = connection!!.inputStream
            val rd = BufferedReader(InputStreamReader(`is`))
            val response = StringBuffer()
            var line: String
            while ((rd.readLine()) != null) {
                line = rd.readLine()
                response.append(line)
                response.append('\r')
            }
            rd.close()
            val responseStr = response.toString()
            Log.d("Server response", responseStr)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (connection != null) {
                connection!!.disconnect()
            }
        }
    }

}