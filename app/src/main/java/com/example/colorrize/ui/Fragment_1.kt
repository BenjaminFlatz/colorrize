package com.example.colorrize.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.colorrize.R
import com.example.colorrize.models.Connection
import com.example.colorrize.models.Devices
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorListener
import kotlinx.android.synthetic.main.activity_main.*
import com.example.colorrize.models.Connection.*



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
    var conn = Connection()



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


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        conn.post_color(colorPickerView.color, i)
                  
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
                    conn.post_color(color, i)
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


