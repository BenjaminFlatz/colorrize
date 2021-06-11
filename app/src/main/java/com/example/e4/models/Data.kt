package com.example.e4.models
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.File
import java.lang.reflect.Type
import java.nio.file.Paths


object Data {

    var devices: MutableList<Device> = arrayListOf()
    var animations: MutableList<Animation> = arrayListOf()
    private var gson = Gson()

    fun checkFile(context: Context?, filename: String): File {
        val file: File = File(context?.filesDir, filename)

        val isNewFileCreated :Boolean = file.createNewFile()
        if(isNewFileCreated){
            println("$filename is created successfully.")
        } else{
            println("$filename already exists.")
        }

        return file
    }
    fun readDevices(context: Context?, filename: String){
        val file = this.checkFile(context, filename)
        val bufferedReader: BufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val listType: Type = object : TypeToken<MutableList<Device?>?>() {}.type
        if (inputString != ""){
            this.devices = this.gson.fromJson(inputString, listType)
            println("readDevices${this.devices}")

        }

    }

    fun writeDevices(context: Context?, filename: String){
        val jsonString:String = this.gson.toJson(this.devices)
        val file = checkFile(context, filename)
        file.writeText(jsonString)
        println("writeDevices${jsonString}")
    }
    fun readAnimations(context: Context?, filename: String){
        val file = this.checkFile(context, filename)
        val bufferedReader: BufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val listType: Type = object : TypeToken<MutableList<Animation?>?>() {}.type
        if (inputString != ""){
            this.animations = this.gson.fromJson(inputString, listType)
            println("readAnimations${this.animations}")

        }

    }

    fun writeAnimations(context: Context?, filename: String){
        val jsonString:String = this.gson.toJson(this.animations)
        val file = checkFile(context, filename)
        file.writeText(jsonString)
        println("writeAnimations${jsonString}")
    }

}
