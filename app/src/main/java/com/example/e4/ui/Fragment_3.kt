package com.example.e4.ui

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebView
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e4.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_anim.*
import kotlinx.android.synthetic.main.activity_anim.rv_anim
import kotlinx.android.synthetic.main.activity_device.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment_3.*


/**
 * this is the Animations Fragment it contains a recyclerView
 * and a floatinActionButton to add items to the recyclerView to
 * delete items there is a onLongClickListener in the Adapter
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment_3.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment_3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_3 : Fragment() {
    var wvAnim: MutableList<WebView> = arrayListOf()
    var recyclerView: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_fragment_3, container, false)
        wvAnim.add(v.findViewById(R.id.wv_anim_0))
        wvAnim.add(v.findViewById(R.id.wv_anim_1))
        wvAnim.add(v.findViewById(R.id.wv_anim_2))
        wvAnim.add(v.findViewById(R.id.wv_anim_3))
        wvAnim.add(v.findViewById(R.id.wv_anim_4))
        wvAnim.add(v.findViewById(R.id.wv_anim_5))
        wvAnim.add(v.findViewById(R.id.wv_anim_6))
        wvAnim.add(v.findViewById(R.id.wv_anim_7))
        wvAnim.add(v.findViewById(R.id.wv_anim_8))
        wvAnim.add(v.findViewById(R.id.wv_anim_9))

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
        recyclerView?.adapter = context?.let { AnimAdapter(it, this.wvAnim) }




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

