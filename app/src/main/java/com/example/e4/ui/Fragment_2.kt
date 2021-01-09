package com.example.e4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.SeekBar

import com.example.e4.models.Devices
import com.example.e4.R


import com.goodiebag.protractorview.ProtractorView
import kotlinx.android.synthetic.main.fragment_fragment_2.*













/**
 * this is the Tunable White fragment that contains two seekBars that control
 * Tunable White value and brightness both of them send the rgb values to the selected ip
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment_2.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment_2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_2 : Fragment() {
    var progressReal = 0
    var brightness = 255

    var r = 0
    var g = 0
    var b = 0
    var m = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_fragment_2, container, false)
        val seekBar = v.findViewById(R.id.sb_tw) as SeekBar
        val protractorView: ProtractorView = v.findViewById(R.id.protractor_view)


        var url = ""
        var wvTW: MutableList<WebView> = arrayListOf()
        wvTW.add(v.findViewById(R.id.wv_tw_0))
        wvTW.add(v.findViewById(R.id.wv_tw_1))
        wvTW.add(v.findViewById(R.id.wv_tw_2))
        wvTW.add(v.findViewById(R.id.wv_tw_3))
        wvTW.add(v.findViewById(R.id.wv_tw_4))
        wvTW.add(v.findViewById(R.id.wv_tw_5))
        wvTW.add(v.findViewById(R.id.wv_tw_6))
        wvTW.add(v.findViewById(R.id.wv_tw_7))
        wvTW.add(v.findViewById(R.id.wv_tw_8))
        wvTW.add(v.findViewById(R.id.wv_tw_9))


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                brightness = seekBar.progress

                r = ((255-progressReal)*brightness)/255
                g = 0
                b = ((progressReal)*brightness)/255

                for (i in Devices.deviceIP.indices) {
                    if (Devices.switch[i]){
                        url = "http://${Devices.deviceIP[i]}/?r${r}g${g}b${b}&"
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
                    brightness = seekBar.progress

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


        return v
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


}

