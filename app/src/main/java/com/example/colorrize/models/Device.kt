package com.example.colorrize.models

class Device {
    var name: String = ""
    var ip: String = ""
    var mode: String = ""
    var state: Boolean = false

    constructor() : super() {}

    constructor (name: String, ip: String, mode: String, state: Boolean) : super() {
        this.name = name
        this.ip = ip
        this.mode = mode
        this.state = state
    }
}

