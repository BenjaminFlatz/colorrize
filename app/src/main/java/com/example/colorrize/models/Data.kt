package com.example.colorrize.models
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.File


object Data {

    var devices: MutableList<Device> = arrayListOf()
    private var gson = Gson()


    fun readDevices(filename: String){
        val file = File(filename)
        if (file.exists()) {
            val bufferedReader: BufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            this.devices = this.gson.fromJson(inputString, this.devices::class.java)
        }
    }

    fun writeDevices(filename: String){
        var jsonString:String = this.gson.toJson(this.devices)
        val file = File(filename)
        file.writeText(jsonString)
    }


}
