package com.example.e4


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
        this.deviceName.add(position, name)
        this.deviceIP.add(position, ip)
        this.switch.add(position, state)
        this.mode.add(position, mode)

    }
    fun addData(name: String, ip: String, mode: String, state: Boolean){

        this.deviceName.add(name)
        this.deviceIP.add(ip)
        this.switch.add(state)
        this.mode.add(mode)

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
        this.deviceName.add(0,"Device 0")
        this.deviceName.add(1,"Device 1")
        this.deviceName.add(2,"Device 2")

        //this.deviceIP = arrayListOf("192.168.43.159", "192.168.43.160", "192.168.43.250")
        this.deviceIP.add(0,"192.168.137.81")
        this.deviceIP.add(1,"192.168.137.87")
        this.deviceIP.add(2,"192.168.43.159")

        //this.switch = arrayListOf(true, true, false)
        this.switch.add(0,true)
        this.switch.add(1,true)
        this.switch.add(2,false)

        this.mode.add(0,"RGB")
        this.mode.add(1,"TW")
        this.mode.add(2,"RGB")


        /*
        for(i in 0..2)
            deviceName.add("Device %.2f".format(i))
            deviceIP.add("123.123.%.2f".format())
*/
    }
}
