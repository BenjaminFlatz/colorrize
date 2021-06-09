package com.example.colorrize.models

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.regex.Pattern


internal object ShowIP {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: ArrayList<String>): String {
        // Create operating system process from arpe.bat file command
        val pb = ProcessBuilder("arpe.bat")

        pb.redirectErrorStream()
        // Starts a new process using the attributes of this process builder
        val p = pb.start()

        val br = BufferedReader(InputStreamReader(p.inputStream))

        // String out is used to store output of this command(process)
        var out = ""

        while (true) {
            var l: String? = null
            try {
                l = br.readLine()
            } catch (ex: IOException) {
            }

            if (l == null)
                break
            out += "\n" + l
        }

        // A compiled representation of a regular expression
        val pattern = Pattern.compile(".*\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b")

        /* An engine that performs match operations on a character sequence by interpreting a Pattern */
        val match = pattern.matcher(out)

        out = ""
        val prev = ""
        var pLoc: String

        if (!match.find())
        // In case no IP address Found in out
            out = "No IP found!"
        else {

            /* Returns the input subsequence matched by the previous match in this case IP of our interface */
            pLoc = match.group()

            out += "$pLoc\nOther Hosts'(In Same Network) IP addresses:\n"
            while (match.find()) {
                pLoc = match.group()    // Returns the IP of other hosts
                out += pLoc + "\n"
            }
            try {
                br.close()
            } catch (ex: IOException) {
            }

        }

        // Printing IP Addresses of all computers in our network
        return out
        println(out)
    }
}