package com.example.colorrize.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar

import com.example.colorrize.models.Devices
import com.example.colorrize.R
import com.example.colorrize.models.Connection


import com.goodiebag.protractorview.ProtractorView
import kotlinx.android.synthetic.main.fragment_fragment_2.*



class Fragment_2 : Fragment() {
    var progressReal = 0
    var brightness = 255

    var r = 0
    var g = 0
    var b = 0
    var m = 0

    val conn = Connection()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_fragment_2, container, false)
        val seekBar = v.findViewById(R.id.sb_tw) as SeekBar
        val protractorView: ProtractorView = v.findViewById(R.id.protractor_view)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                brightness = seekBar.progress

                r = ((255-progressReal)*brightness)/255
                g = 0
                b = ((progressReal)*brightness)/255
                var color = Color.rgb(r,g,b)

                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        conn.post_color(color, i)

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
                    brightness = seekBar.progress

                    var r = ((255-progressReal)*brightness)/1000
                    var g = 0
                    var b = ((progressReal)*brightness)/1000

                    var colorTemp = progressReal*6500/255

                    tv_color_tw.text = "$colorTemp K"
                    var color = Color.rgb(r,g,b)

                    for (i in Devices.deviceIP.indices) {
                        if (Devices.switch[i]){
                            conn.post_color(color, i)
                            //Toast.makeText(this@TWActivity, "$i", Toast.LENGTH_SHORT).show()

                        }

                    }

                }

                override fun onStartTrackingTouch(pv: ProtractorView) {

                }

                override fun onStopTrackingTouch(pv: ProtractorView) {

                }
            }


        return v
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}

