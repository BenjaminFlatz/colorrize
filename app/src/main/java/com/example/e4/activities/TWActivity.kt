package com.example.e4.activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

import kotlinx.android.synthetic.main.activity_tw.*
import com.goodiebag.protractorview.ProtractorView
import android.widget.SeekBar
import com.example.e4.R
import com.example.e4.models.Devices


@Suppress("DEPRECATION")
class TWActivity : AppCompatActivity() {

    private lateinit var bitmap: Bitmap
    var progressReal = 0
    var brightness = 1000

    var r = 0
    var g = 0
    var b = 0
    var m = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tw)


        val protractorView: ProtractorView = findViewById<ProtractorView>(R.id.protractor_view)
        val seekBarView = sb_tw

        var url = ""
        var wvTW: MutableList<WebView> = arrayListOf()
        wvTW.add(wv_tw_0)
        wvTW.add(wv_tw_1)
        wvTW.add(wv_tw_2)
        wvTW.add(wv_tw_3)
        wvTW.add(wv_tw_4)
        wvTW.add(wv_tw_5)
        wvTW.add(wv_tw_6)
        wvTW.add(wv_tw_7)
        wvTW.add(wv_tw_8)
        wvTW.add(wv_tw_9)
        
        //This can then be added to its parent layout
        /*color_layout_tw.addView(protractorView)
        protractorView.tickIntervals = 90
        protractorView.arcColor = getColor(R.color.colorAccent)
        protractorView.progressColor = getColor(R.color.colorAccent1)
        val thumb = ShapeDrawable(OvalShape())

        thumb.intrinsicHeight = 80
        thumb.intrinsicWidth = 30
        protractorView.thumb = thumb
        */

        seekBarView?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                brightness = seekBar.progress

                r = ((255-progressReal)*brightness)/1000
                g = 0
                b = ((progressReal)*brightness)/1000

                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        url = "http://${Devices.deviceIP[i]}/?r${r}g${g}b${b}m$m&"
                        wvTW[i].settings.javaScriptEnabled = true
                        wvTW[i].loadUrl(url)
                        //Toast.makeText(this@TWActivity, "$i", Toast.LENGTH_SHORT).show()

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

        protractorView.onProtractorViewChangeListener =
            object : ProtractorView.OnProtractorViewChangeListener {
                override fun onProgressChanged(pv: ProtractorView, progress: Int, b: Boolean) {
                    progressReal = progress*255/180
                    brightness = seekBarView.progress

                    var r = ((255-progressReal)*brightness)/1000
                    var g = 0
                    var b = ((progressReal)*brightness)/1000

                    var colorTemp = progressReal*6500/255

                    tv_color_tw.text = "$colorTemp K"


                    for (i in Devices.deviceIP.indices) {
                        if (Devices.switch[i]){
                            url = "http://${Devices.deviceIP[i]}/?r${r}g${g}b${b}m$m&"
                            wvTW[i].settings.javaScriptEnabled = true
                            wvTW[i].loadUrl(url)
                            //Toast.makeText(this@TWActivity, "$i", Toast.LENGTH_SHORT).show()

                        }

                    }

                }

                override fun onStartTrackingTouch(pv: ProtractorView) {

                }

                override fun onStopTrackingTouch(pv: ProtractorView) {

                }
            }

        /*iv_tw.isDrawingCacheEnabled = true
        iv_tw.buildDrawingCache(true)

        iv_tw.setOnTouchListener { _, event ->

            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {


                bitmap = iv_tw.drawingCache
                val pixel = bitmap.getPixel(event.x.toInt(), event.y.toInt())

                var r = Color.red(pixel)
                var g = Color.green(pixel)
                var b = Color.blue(pixel)


                val hex = "#" + Integer.toHexString(pixel)

                //color_layout.setBackgroundColor(Color.rgb(r, g, b))
                color_view.text = "RGB: $r, $g, $b, \nHex: $hex\n${Devices.deviceIP[0]}"

                //Toast.makeText(this@TWActivity, Devices.deviceIP[0], Toast.LENGTH_SHORT).show()
                for (i in 0 until Devices.deviceIP.size) {
                    if (Devices.switch[i]){
                        wv_tw.loadUrl("http://${Devices.deviceIP[i]}/?r${r.toString()}g${g.toString()}b${b.toString()}&")
                        Toast.makeText(this@TWActivity, Devices.deviceIP[i], Toast.LENGTH_SHORT).show()
                        //sleep(100)

                    }

                }

            }
            true


        }*/


    }

}

