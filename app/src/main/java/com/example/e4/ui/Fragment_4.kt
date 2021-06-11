package com.example.e4.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e4.*
import com.example.e4.adapter.DeviceAdapter
import com.example.e4.models.Data
import com.example.e4.models.Device
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_fragment_4.*



class Fragment_4 : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val ADD_TASK_REQUEST = 1

    var recyclerView: RecyclerView? = null

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

        val fabDevice = v.findViewById<FloatingActionButton>(R.id.fab_device)
        val fab1 = v.findViewById<FloatingActionButton>(R.id.fab_1)
        val fab2 = v.findViewById<FloatingActionButton>(R.id.fab_2)

        val tvFab1 = v.findViewById<TextView>(R.id.tv_fab1)
        val tvFab2 = v.findViewById<TextView>(R.id.tv_fab2)



        tvFab1.visibility = View.GONE
        tvFab2.visibility = View.GONE


        fab2.setOnClickListener {
            Toast.makeText(context, ":D", Toast.LENGTH_SHORT).show()

        }


        fab1.setOnClickListener {
            showAddDialog()

        }
        fabDevice.setOnClickListener {
            if (!isFabOpen) {
                isFabOpen = true
                tvFab1.visibility = View.VISIBLE
                tvFab2.visibility = View.VISIBLE
                fabLayout_1?.visibility = View.VISIBLE
                fabLayout_2?.visibility = View.VISIBLE
                fabLayout?.visibility = View.VISIBLE
                fabDevice?.animate()?.rotationBy(180F)
                fabLayout_1?.animate()?.translationY(-resources.getDimension(R.dimen.standard_55))
                fabLayout_2?.animate()?.translationY(-resources.getDimension(R.dimen.standard_105))

            } else {
                isFabOpen = false
                tvFab1.visibility = View.GONE
                tvFab2.visibility = View.GONE
                fabDevice?.animate()?.rotation(0F)
                fabLayout_1?.animate()?.translationY(0F)
                fabLayout_2?.animate()?.translationY(0F)

                }
            }
        return v
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

                var device = Device(name = dialogName.text.toString(), ip = dialogIp.text.toString(), mode = mode, state = false)
                Data.devices.add(device)
                Data.writeDevices(context, "devices.json")

                recyclerView?.adapter?.notifyDataSetChanged()
                println("Fragment_4${Data.devices}")


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