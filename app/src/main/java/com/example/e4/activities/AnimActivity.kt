package com.example.e4.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.webkit.WebView
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e4.adapter.AnimAdapter
import com.example.e4.models.Animations
import com.example.e4.R
import kotlinx.android.synthetic.main.activity_anim.*

class AnimActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)


        var wvAnim: MutableList<WebView> = arrayListOf()
        wvAnim.add(wv_anim_0)
        wvAnim.add(wv_anim_1)
        wvAnim.add(wv_anim_2)
        wvAnim.add(wv_anim_3)
        wvAnim.add(wv_anim_4)
        wvAnim.add(wv_anim_5)
        wvAnim.add(wv_anim_6)
        wvAnim.add(wv_anim_7)
        wvAnim.add(wv_anim_8)
        wvAnim.add(wv_anim_9)



        val recyclerView = findViewById<RecyclerView>(R.id.rv_anim)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AnimAdapter(this, wvAnim)


        fab_add_anim.setOnClickListener(){

            showAddDialog()


        }



    }
    private fun showAddDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.add_animation)
        //dialog.window!!.setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //dialog.window.setLayout(600, 400);
        var mode = ""

        val addBtn = dialog.findViewById(R.id.dialog_anim_add_btn) as Button
        val cancelBtn = dialog.findViewById(R.id.dialog_anim_cancel_btn) as Button

        val sbFrequency = dialog.findViewById(R.id.dialog_anim_frequency) as SeekBar
        val sbBrightness = dialog.findViewById(R.id.dialog_anim_brightness) as SeekBar

        val swSine = dialog.findViewById(R.id.sw_sine) as Switch
        val swRect = dialog.findViewById(R.id.sw_rect) as Switch
        val swTri = dialog.findViewById(R.id.sw_tri) as Switch

        val etName = dialog.findViewById(R.id.dialog_anim_name) as EditText

        etName.isFocusableInTouchMode = true



        swSine.setOnClickListener(){
            if (swSine.isChecked){
                swRect.isChecked = false
                swTri.isChecked = false
                mode = "sine"

            }

        }
        swRect.setOnClickListener(){
            if (swRect.isChecked){
                swSine.isChecked = false
                swTri.isChecked = false
                mode = "rect"

            }
        }
        swTri.setOnClickListener(){
            if (swTri.isChecked){
                swRect.isChecked = false
                swSine.isChecked = false
                mode = "tri"

            }
        }

        //dialogName.requestFocus()

        addBtn.setOnClickListener {

            Animations.addData(
                etName.text.toString(),
                mode,
                sbFrequency.progress,
                sbBrightness.progress,
                false
            )
            /*
            Animations.frequency.add(sbFrequency.progress)
            Animations.brightness.add(sbBrightness.progress)
            Animations.mode.add(mode)
            Animations.animName.add(etName.text.toString())
*/

            dialog.cancel()

        }
        cancelBtn.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()

    }

}
