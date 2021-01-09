package com.example.e4.ui

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e4.*
import com.example.e4.adapter.DeviceAdapter
import com.example.e4.models.Devices
import com.example.e4.models.NetworkSniffTask
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_device.rv_devices


/**
 * this is the device Fragment it contains a recyclerView
 * and a floatingActionButton to add items to the recyclerView
 * to delete items there's a onLongClickListener in the fragments Adapter
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Fragment_4.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Fragment_4.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_4 : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val ADD_TASK_REQUEST = 1

    var recyclerView: RecyclerView? = null
/*
    val fabLayout = null
    val fabLayout_1 = null
    val fabLayout_2 = null


    val fab = null
    val fab1 = null
    val fab2 = null

    val tvFab1 = null
    val tvFab2 = null
*/
    var isFabOpen = false


    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_fragment_4, container, false)

        val fabLayout = v.findViewById<LinearLayout>(R.id.fabLayout)
        val fabLayout_1 = v.findViewById<LinearLayout>(R.id.fabLayout_1)
        val fabLayout_2 = v.findViewById<LinearLayout>(R.id.fabLayout_2)
        val fabLayout_3 = v.findViewById<LinearLayout>(R.id.fabLayout_3)

        val fabDevice = v.findViewById<FloatingActionButton>(R.id.fab_device)
        val fab1 = v.findViewById<FloatingActionButton>(R.id.fab_1)
        val fab2 = v.findViewById<FloatingActionButton>(R.id.fab_2)
        val fab3 = v.findViewById<FloatingActionButton>(R.id.fab_3)

        val tvFab1 = v.findViewById<TextView>(R.id.tv_fab1)
        val tvFab2 = v.findViewById<TextView>(R.id.tv_fab2)
        val tvFab3 = v.findViewById<TextView>(R.id.tv_fab3)

        tvFab1.visibility = View.GONE
        tvFab2.visibility = View.GONE
        tvFab3.visibility = View.GONE

        fab3.setOnClickListener {
            val sniffer = NetworkSniffTask()
            sniffer.getAddress()

            //Devices.addData(ips[0], out, "RGB", false)

            recyclerView?.adapter?.notifyDataSetChanged()
            //run {
            //  IntentIntegrator(context as Activity?).initiateScan()
            //}

        }
        fab2.setOnClickListener {
            val sniffer = NetworkSniffTask()
            context?.let { it1 -> sniffer.NetworkSniffTask(it1) }
            sniffer.execute()

            //Devices.addData(ips[0], out, "RGB", false)

            recyclerView?.adapter?.notifyDataSetChanged()
            //run {
            //  IntentIntegrator(context as Activity?).initiateScan()
            //}

        }


        fab1.setOnClickListener {
            showAddDialog()

        }
        fabDevice.setOnClickListener {
            if (!isFabOpen) {
                isFabOpen = true
                tvFab1.visibility = View.VISIBLE
                tvFab2.visibility = View.VISIBLE
                tvFab3.visibility = View.VISIBLE
                fabLayout_1?.visibility = View.VISIBLE
                fabLayout_2?.visibility = View.VISIBLE
                fabLayout_3?.visibility = View.VISIBLE
                fabLayout?.visibility = View.VISIBLE
                fabDevice?.animate()?.rotationBy(180F)
                fabLayout_1?.animate()?.translationY(-resources.getDimension(R.dimen.standard_55))
                fabLayout_2?.animate()?.translationY(-resources.getDimension(R.dimen.standard_105))
                fabLayout_3?.animate()?.translationY(-resources.getDimension(R.dimen.standard_155))

            } else {
                isFabOpen = false
                tvFab1.visibility = View.GONE
                tvFab2.visibility = View.GONE
                tvFab3.visibility = View.GONE
                fabDevice?.animate()?.rotation(0F)
                fabLayout_1?.animate()?.translationY(0F)
                fabLayout_2?.animate()?.translationY(0F)
                fabLayout_3?.animate()?.translationY(0F)?.setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animator: Animator) {

                    }

                    override fun onAnimationEnd(animator: Animator) {
                        if (!isFabOpen) {
                            fabLayout_1?.visibility = View.GONE
                            fabLayout_2.visibility = View.GONE
                            fabLayout_3.visibility = View.GONE
                        }
                        /*                if (fab.getRotation() != -180) {
                                            fab.setRotation(-180);
                                        }*/
                    }

                    override fun onAnimationCancel(animator: Animator) {

                    }

                    override fun onAnimationRepeat(animator: Animator) {

                    }
                })
            }
        }
        return v
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result: IntentResult? =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {

            if (result.contents != null) {
                Toast.makeText(context, "${result.contents}", Toast.LENGTH_SHORT)
                    .show()
                //Devices.addData()

            } else {
                Toast.makeText(context, "scan failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = rv_devices
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = context?.let { DeviceAdapter(it) }


    }
    private fun showAddDialog(): Boolean {
        val dialog = context?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(true)
        dialog?.setContentView(R.layout.add_device)
        //dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //dialog.window.setLayout(600, 400);

        val dialogName = dialog?.findViewById(R.id.dialog_name) as EditText
        val dialogIp = dialog.findViewById(R.id.dialog_ip) as EditText

        dialogName.isFocusableInTouchMode = true
        dialogIp.isFocusableInTouchMode = true
        val addBtn = dialog.findViewById(R.id.dialog_add_btn) as Button
        val cancelBtn = dialog.findViewById(R.id.dialog_cancel_btn) as Button
        dialogName.requestFocus()

        val swRGB = dialog.findViewById(R.id.sw_dialog_rgb) as Switch
        val swTW = dialog.findViewById(R.id.sw_dialog_tw) as Switch

        var mode = ""

        swRGB.setOnClickListener {
            if (swRGB.isChecked){
                swTW.isChecked = false
                mode = "RGB"

            }

        }
        swTW.setOnClickListener {
            if (swTW.isChecked){
                swRGB.isChecked = false
                mode = "TW"

            }

        }


        addBtn.setOnClickListener {
            //dialog.dismiss()
            if (dialogName.text.toString() != "" && dialogIp.text.toString() != "" && mode != ""){
                Devices.addData(dialogName.text.toString(), dialogIp.text.toString(), mode, false)
                recyclerView?.adapter?.notifyDataSetChanged()


            }
            else{
                Toast.makeText(context,"please enter all values", Toast.LENGTH_SHORT).show()

            }
            //Devices.deviceIP.add(dialogIp.text.toString())
            dialog.cancel()

        }
        cancelBtn.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()

        return true
    }








}