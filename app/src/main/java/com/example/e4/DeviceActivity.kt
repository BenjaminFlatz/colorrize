package com.example.e4

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_device.*
import kotlinx.android.synthetic.main.activity_device.fab
import kotlinx.android.synthetic.main.activity_device.fab_2
import kotlinx.android.synthetic.main.activity_main.*


class DeviceActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val ADD_TASK_REQUEST = 1
    var isFabRightOpen = false
    var isFabLeftOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {2
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_devices)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DeviceAdapter(this)


        fab_2.setOnClickListener {
            run {
                IntentIntegrator(this@DeviceActivity).initiateScan()
            }

        }
        fab_1.setOnClickListener {
            val intent = Intent(this@DeviceActivity, DeviceActivity::class.java)
            startActivity(intent)

        }


    }


    private fun showAddDialog(): Boolean {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.add_device)
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //dialog.window.setLayout(600, 400);

        val dialogName = dialog.findViewById(R.id.dialog_name) as EditText
        val dialogIp = dialog.findViewById(R.id.dialog_ip) as EditText

        dialogName.isFocusableInTouchMode = true
        dialogIp.isFocusableInTouchMode = true
        val addBtn = dialog.findViewById(R.id.dialog_add_btn) as Button
        val cancelBtn = dialog.findViewById(R.id.dialog_cancel_btn) as Button
        dialogName.requestFocus()

        val swRGB = dialog.findViewById(R.id.sw_dialog_rgb) as Switch
        val swTW = dialog.findViewById(R.id.sw_dialog_tw) as Switch

        var mode = ""

        swRGB.setOnClickListener(){
            if (swRGB.isChecked){
                swTW.isChecked = false
                mode = "RGB"

            }

        }
        swTW.setOnClickListener(){
            if (swTW.isChecked){
                swRGB.isChecked = false
                mode = "TW"

            }

        }


        addBtn.setOnClickListener {
            //dialog.dismiss()
            Devices.addData(dialogName.text.toString(), dialogIp.text.toString(), mode, false)
            //Devices.deviceIP.add(dialogIp.text.toString())
            dialog.cancel()

        }
        cancelBtn.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()

        return false
    }

}