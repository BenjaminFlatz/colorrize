package com.example.e4.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.graphics.Bitmap
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.WebView
import android.widget.SeekBar
import com.example.e4.models.Devices
import com.example.e4.R
import com.skydoves.colorpickerview.ColorPickerView


import com.skydoves.colorpickerview.listeners.ColorListener






/**
 * this is the RGB Fragment contains a colorPicker with grey selector wheel
 * the selected is sent to the ESP8266 Module via loading the url with the ip and selected rgb values
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment_1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment_1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_1 : Fragment() {
    private lateinit var bitmap: Bitmap
    var brightness = 255
    var r = 0
    var g = 0
    var b = 0
    var m = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_fragment_1, container, false)
        val seekBar = v.findViewById(R.id.sb_rgb) as SeekBar
        val colorPickerView = v.findViewById(R.id.color_picker_view) as ColorPickerView
        //v.findViewById<ImageView>(R.id.iv_rgb).isDrawingCacheEnabled = true
        //v.findViewById<ImageView>(R.id.iv_rgb).buildDrawingCache(true)

        var url = ""
        var wvRGB: MutableList<WebView> = arrayListOf()
        wvRGB.add(v.findViewById(R.id.wv_rgb_0))
        wvRGB.add(v.findViewById(R.id.wv_rgb_1))
        wvRGB.add(v.findViewById(R.id.wv_rgb_2))
        wvRGB.add(v.findViewById(R.id.wv_rgb_3))
        wvRGB.add(v.findViewById(R.id.wv_rgb_4))
        wvRGB.add(v.findViewById(R.id.wv_rgb_5))
        wvRGB.add(v.findViewById(R.id.wv_rgb_6))
        wvRGB.add(v.findViewById(R.id.wv_rgb_7))
        wvRGB.add(v.findViewById(R.id.wv_rgb_8))
        wvRGB.add(v.findViewById(R.id.wv_rgb_9))

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                brightness = seekBar.progress

                r = (Color.red(colorPickerView.color)*brightness)/255
                g = (Color.green(colorPickerView.color)*brightness)/255
                b = (Color.blue(colorPickerView.color)*brightness)/255

                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        url = "http://${Devices.deviceIP[i]}/?r${r}g${g}b${b}&"
                        wvRGB[i].settings.javaScriptEnabled = true
                        wvRGB[i].loadUrl(url)
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

        colorPickerView.setColorListener(ColorListener { color, _ ->
            brightness = seekBar.progress

            r = (Color.red(color)*brightness)/1000
            g = (Color.green(color)*brightness)/1000
            b = (Color.blue(color)*brightness)/1000

            val hex = "#" + Integer.toHexString(color)




            for (i in Devices.deviceIP.indices) {
                if (Devices.switch[i]){
                    url = "http://${Devices.deviceIP[i]}/?r${r}g${g}b${b}m$m&"
                    wvRGB[i].settings.javaScriptEnabled = true
                    wvRGB[i].loadUrl(url)
                    //Toast.makeText(this@RGBActivity, "${Devices.deviceIP[i]}", Toast.LENGTH_SHORT).show()
                }


            }

        })
/*
        v.iv_rgb.setOnTouchListener { _, event ->


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
*/

        return v
    }

}


