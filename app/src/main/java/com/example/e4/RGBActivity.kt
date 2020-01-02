package com.example.e4

import android.bluetooth.BluetoothClass
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_rgb.*
import kotlinx.android.synthetic.main.device_item.*
import java.io.File
import java.lang.Thread.sleep
import java.net.HttpURLConnection
import java.net.URL
import org.jetbrains.anko.toast
import java.util.*
import android.webkit.WebView

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.SeekBar
import android.util.Log
import android.webkit.WebChromeClient


@Suppress("DEPRECATION")
class RGBActivity : AppCompatActivity() {

    private lateinit var bitmap: Bitmap
    var brightness = 1000
    var r = 0
    var g = 0
    var b = 0
    var m = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rgb)

        iv_rgb.isDrawingCacheEnabled = true
        iv_rgb.buildDrawingCache(true)


        //val seekBarView = sb_rgb
        var url = ""
        var wvRGB: MutableList<WebView> = arrayListOf()
        wvRGB.add(wv_rgb_0)
        wvRGB.add(wv_rgb_1)
        wvRGB.add(wv_rgb_2)
        wvRGB.add(wv_rgb_3)
        wvRGB.add(wv_rgb_4)
        wvRGB.add(wv_rgb_5)
        wvRGB.add(wv_rgb_6)
        wvRGB.add(wv_rgb_7)
        wvRGB.add(wv_rgb_8)
        wvRGB.add(wv_rgb_9)


        //
        //wvRGB.webViewClient = WebViewClient()

        /*seekBarView?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                brightness = seekBar.progress

                r = (r*brightness)/1000
                g = (g*brightness)/1000
                b = (b*brightness)/1000

                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        url = "http://${Devices.deviceIP[i]}/?r${r.toString()}g${g.toString()}b${b.toString()}&"
                        wvRGB[i].settings.javaScriptEnabled = true
                        wvRGB[i].loadUrl(url)
                        //Toast.makeText(this@RGBActivity, "${Devices.deviceIP[i]}", Toast.LENGTH_SHORT).show()
                    }


                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is started.
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Write code to perform some action when touch is stopped.
                //Toast.makeText(this@TWActivity, "Progress is " + seekBar.progress + "%", Toast.LENGTH_SHORT).show()
            }
        })
*/
        iv_rgb.setOnTouchListener { _, event ->

            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {

                bitmap = iv_rgb.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                r = (Color.red(pixel)*brightness)/1000
                g = (Color.green(pixel)*brightness)/1000
                b = (Color.blue(pixel)*brightness)/1000

                val hex = "#" + Integer.toHexString(pixel)




                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        url = "http://${Devices.deviceIP[i]}/?r${r}g${g}b${b}m$m&"
                        wvRGB[i].settings.javaScriptEnabled = true
                        wvRGB[i].loadUrl(url)
                        //Toast.makeText(this@RGBActivity, "${Devices.deviceIP[i]}", Toast.LENGTH_SHORT).show()
                    }


                }

            }
            true


        }


    }

}
