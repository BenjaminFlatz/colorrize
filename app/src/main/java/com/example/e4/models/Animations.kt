package com.example.e4.models

import android.annotation.SuppressLint

@SuppressLint("StaticFieldLeak")
object Animations{

    var animName: MutableList<String> = arrayListOf()
    //var ledMode: MutableList<String> = arrayListOf()
    var mode: MutableList<String> = arrayListOf()
    var frequency: MutableList<Int> = arrayListOf()
    var brightness: MutableList<Int> = arrayListOf()
    var switch: MutableList<Boolean> = arrayListOf()

    fun removeData(position: Int){
        animName.removeAt(position)
        mode.removeAt(position)
        frequency.removeAt(position)
        brightness.removeAt(position)
        switch.removeAt(position)


    }
    fun addDataPosition(name: String, mode: String, frequency: Int, brightness: Int, state: Boolean, position: Int){
        animName.add(position, name)
        Animations.mode.add(position, mode)
        Animations.frequency.add(position, frequency)
        Animations.brightness.add(position, brightness)
        switch.add(position, state)

    }
    fun addData(name: String, mode: String, frequency: Int, brightness: Int, state: Boolean){
        animName.add(name)
        Animations.mode.add(mode)
        Animations.frequency.add(frequency)
        Animations.brightness.add(brightness)
        switch.add(state)

    }
    fun add_arguments(){
        animName.add("sine")
        animName.add("rect")
        animName.add("tri")

        mode.add("sine")
        mode.add("rect")
        mode.add("tri")

        frequency.add(10)
        frequency.add(20)
        frequency.add(30)

        brightness.add(255)
        brightness.add(255)
        brightness.add(255)

        switch.add(false)
        switch.add(false)
        switch.add(false)

    }

    fun control_leds(){


    }

    fun animate_rgb(){




    }
    fun animate_tw(){




    }



}
