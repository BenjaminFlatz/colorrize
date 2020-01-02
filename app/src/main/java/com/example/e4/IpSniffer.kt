package com.example.e4

import java.io.IOException
import java.net.InetAddress

class IpSniffer (){

    fun main(){
        val subnet = "172.23.93"
        for (i in 0..255) {//VERY INNEFICENT! Takes about 13 minutes to go through all hosts on ONE subnet(at 3000ms timeout)
            val host = "$subnet.$i"
            try {
                if (InetAddress.getByName(host).isReachable(1000)) {
                    println("$host is reachable")
                    Devices.addData(host, host, "RGB", false)
                }
                else {
                    println("$host is not reachable")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }


    }






}