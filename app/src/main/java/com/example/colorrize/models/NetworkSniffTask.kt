package com.example.colorrize.models

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.provider.SyncStateContract
import android.text.format.Formatter
import android.util.Log
import java.io.*
import java.lang.ref.WeakReference
import java.net.InetAddress


open class NetworkSniffTask() : AsyncTask<Void, Void, Void>() {
    //var ip = arrayListOf<String>()
    //var mac = arrayListOf<String>()

    override fun doInBackground(vararg params: Void?): Void? {
        Log.d(TAG, "Let's sniff the network")

        try {
            val context = mContextRef.get()

            if (context != null) {

                val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                val wm = context!!.getSystemService(Context.WIFI_SERVICE) as WifiManager

                val connectionInfo = wm.connectionInfo
                val ipAddress = connectionInfo.ipAddress
                val ipString = Formatter.formatIpAddress(ipAddress)


                Log.d(TAG, "activeNetwork: $activeNetwork")
                Log.d(TAG, "ipString: $ipString")

                val prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1)
                Log.d(TAG, "prefix: $prefix")

                for (i in 0..254) {
                    val testIp = prefix + i.toString()

                    Log.d(TAG, "count: $i")
                    val address = InetAddress.getByName(testIp)
                    val reachable = address.isReachable(1000)
                    val hostName = address.canonicalHostName

                    if (reachable) {
                        var mac = getMacAddress()
                        for (i in mac?.indices!!){

                            mac?.let { Devices.addData(it[i].toString(), address.toString(), "RGB", false) }


                        }
                        Log.d(TAG, "Mac: ${Devices.deviceName}")
                        Log.d(TAG, "Mac: ${Devices.deviceIP}")
                        Log.i(
                            TAG,
                            "Host: $hostName($testIp) is reachable!"
                        )
                    }
                    else{
                        Log.d(TAG, "Host: $hostName($testIp) is not reachable!")
                    }
                }
            }
        } catch (t: Throwable) {
            Log.e(TAG, "Well that's not good.", t)
        }

        return null
    }

    private val TAG = SyncStateContract.Constants.DATA + "nstask"

    private lateinit var mContextRef: WeakReference<Context>

    fun NetworkSniffTask(context: Context) {
        mContextRef = WeakReference<Context>(context)
    }
    public fun getMacAddress(): List<String>? {
        try {
            val br = BufferedReader(FileReader(File("/proc/net/arp")))
            var line: String
            while ((br.readLine()) != null) {
                line = br.readLine()
                val parts = line.split("    ")
                for (i in parts.indices){
                    //if (parts[i] == ipAddress) {
                    var ipPart = parts[i].split(" ")

                    for (j in Devices.deviceIP.indices){
                        if (Devices.deviceIP[j] == ipPart[i])
                            return null

                    }

                    return parts
                    //this.mac.add(parts[i+4])
                    //this.ip.add(parts[i])
                    //return parts[i+5]
                    //}

                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
    public fun getAddress(): String? {
        try {
            val br = BufferedReader(FileReader(File("/proc/net/arp")))
            var line: String
            while ((br.readLine()) != null) {
                line = br.readLine()
                val parts = line.split("    ")
                for (i in parts.indices){
                    //if (parts[i] == ipAddress) {
                    var ipPart = parts[i].split(" ")

                    for (j in Devices.deviceIP.indices) {
                        if (Devices.deviceIP[j] == ipPart[i]) {
                            return null
                        }
                    }


                    "..:..:..:..:..:..".sumBy {
                        if (parts[i].contains(it)) {
                            Devices.addData(parts[i], ipPart[i], "RGB", false)
                            1
                        }
                        else
                            0
                    }

                    //this.mac.add(parts[i+4])

                    //this.ip.add(parts[i])
                    return parts[i+5]
                    //}

                }

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
    private fun getMacAddressFromIP(ipFinding: String): String {

        Log.i("IPScanning", "Scan was started!")
        val antarDevicesInfos = arrayListOf<String>()


        var bufferedReader: BufferedReader? = null

        try {
            bufferedReader = BufferedReader(FileReader("/proc/net/arp"))

            var line: String = ""
            while ((bufferedReader.readLine()) != null) {
                val splitted =
                    line.split(" +".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (splitted != null && splitted.size >= 4) {
                    val ip = splitted[0]
                    val mac = splitted[3]
                    if (mac.matches("..:..:..:..:..:..".toRegex())) {

                        if (ip.equals(ipFinding, ignoreCase = true)) {
                            return mac
                        }
                    }
                }
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bufferedReader!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        return "00:00:00:00"
    }
    @SuppressLint("HardwareIds")
    fun startPingService(context: Context) {

        val deviceInfoList = ArrayList<String>()
        try {

            val mWifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val mWifiInfo = mWifiManager.connectionInfo
            val subnet = getSubnetAddress(mWifiManager.dhcpInfo.gateway)
            val macAddr = mWifiInfo.macAddress

            for (i in 1..254) {

                val host = "$subnet.$i"

                if (InetAddress.getByName(host).isReachable(1000)) {

                    val strMacAddress = getMacAddressFromIP(host)

                    Log.w(
                        "DeviceDiscovery",
                        "Reachable Host: $host and Mac : $strMacAddress is reachable!"
                    )

                    val localDeviceInfo = ("${host}, $strMacAddress")
                    deviceInfoList.add(localDeviceInfo)
                    Devices.addData(strMacAddress, host, "RGB", false)
                } else {
                    Log.e("DeviceDiscovery", "❌ Not Reachable Host: $host")

                }
            }


        } catch (e: Exception) {
            //System.out.println(e);
        }


    }
    private fun getSubnetAddress(address: Int): String {

        return String.format(
            "%d.%d.%d",
            address and 0xff,
            address shr 8 and 0xff,
            address shr 16 and 0xff
        )
    }

}
