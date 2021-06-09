package com.example.colorrize.models

import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences





object Devices {

    var deviceName: MutableList<String> = arrayListOf()
    var deviceIP: MutableList<String> = arrayListOf()
    var switch: MutableList<Boolean> = arrayListOf()
    var mode: MutableList<String> = arrayListOf()

    fun removeData(position: Int){
        deviceName.removeAt(position)
        deviceIP.removeAt(position)
        switch.removeAt(position)
        mode.removeAt(position)

    }
    fun addDataPosition(name: String, ip: String, mode: String, state: Boolean, position: Int){
        deviceName.add(position, name)
        deviceIP.add(position, ip)
        switch.add(position, state)
        Devices.mode.add(position, mode)

    }
    fun addData(name: String, ip: String, mode: String, state: Boolean){

        deviceName.add(name)
        deviceIP.add(ip)
        switch.add(state)
        Devices.mode.add(mode)

    }

    fun getName(i:Int): String {
        return deviceName[i]

    }
    fun getIp(i:Int): String {
        return deviceIP[i]

    }
    fun getState(i:Int): Boolean {
        return switch[i]

    }


    fun addNames(){
        //this.deviceName = arrayListOf("Device 0", "Device 1", "Device 2")
        deviceName.add(0,"Device 0")
        deviceName.add(1,"Device 1")
        deviceName.add(2,"Device 2")

        //this.deviceIP = arrayListOf("192.168.43.159", "192.168.43.160", "192.168.43.250")
        deviceIP.add(0,"192.168.137.81")
        deviceIP.add(1,"192.168.137.87")
        deviceIP.add(2,"192.168.43.159")

        //this.switch = arrayListOf(true, true, false)
        switch.add(0,true)
        switch.add(1,true)
        switch.add(2,false)

        mode.add(0,"RGB")
        mode.add(1,"TW")
        mode.add(2,"RGB")


        /*
        for(i in 0..2)
            deviceName.add("Device %.2f".format(i))
            deviceIP.add("123.123.%.2f".format())
*/
    }
}
