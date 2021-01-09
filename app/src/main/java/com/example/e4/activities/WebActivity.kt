package com.example.e4.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e4.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val button = btn_enter
        val textView = tv_ip
        val webView = wv_ip
        wv_ip.loadUrl("192.168.43.159")

    }


}



