package com.example.colorrize.ui

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colorrize.*
import com.example.colorrize.adapter.AnimAdapter
import com.example.colorrize.models.Animations
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_fragment_3.*


class Fragment_3 : Fragment() {
    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_fragment_3, container, false)

        val fabAddAnim = v.findViewById<FloatingActionButton>(R.id.fab_add_anim)
        fabAddAnim.setOnClickListener(){

            showAddDialog()


        }
        // Inflate the layout for this fragment
        return v
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //val recyclerView = rv_anim
        //recyclerView.layoutManager = LinearLayoutManager(context)
        //recyclerView.adapter = context?.let { AnimAdapter(it,this.wvAnim) }

        recyclerView = rv_anim
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = context?.let { AnimAdapter(it) }




    }
    private fun showAddDialog() {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        dialog?.setContentView(R.layout.add_animation)
        //dialog?.window!!.setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //dialog.window.setLayout(600, 400);
        var mode = ""

        val addBtn = dialog?.findViewById(R.id.dialog_anim_add_btn) as Button
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


            if(etName.text.toString() != "" && mode != "" && sbFrequency.progress != 0 && sbBrightness.progress != 0) {
                Animations.addData(
                    etName.text.toString(),
                    mode,
                    sbFrequency.progress,
                    sbBrightness.progress,
                    false
                )
                recyclerView?.adapter?.notifyDataSetChanged()

            }
            else{
                Toast.makeText(context,"please enter all values", Toast.LENGTH_SHORT).show()

            }
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

