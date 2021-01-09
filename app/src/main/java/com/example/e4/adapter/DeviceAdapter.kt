package com.example.e4.adapter

import android.app.Dialog
import android.content.Context
import android.os.Vibrator
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.e4.models.Devices
import com.example.e4.R
import com.example.e4.activities.DeviceActivity
import kotlinx.android.synthetic.main.device_item.view.*


@Suppress("DEPRECATION")
class DeviceAdapter(val context: Context)  : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {


    var onItemClick: ((DeviceActivity) -> Unit)? = null


    override fun getItemCount(): Int {
        return Devices.deviceName.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //clear()
/*
        val url = URL("${holder.tvDeviceIP.text}")
        val conn = url.openConnection()

        val headerFields = conn.headerFields

        val headerFieldsSet = headerFields.keys
        val headerFieldsIter = headerFieldsSet.iterator()


        while (headerFieldsIter.hasNext()) {

            val headerFieldKey = headerFieldsIter.next()
            val headerFieldValue = headerFields[headerFieldKey]

            val sb = StringBuilder()
            for (value in headerFieldValue!!) {
                sb.append(value)
                sb.append("")
            }

            println("$headerFieldKey=$sb")

        }*/



        if (Devices.mode[position] == "RGB"){
            holder.ivDevice.setBackgroundResource(R.drawable.ic_rgb)

        }
        else if (Devices.mode[position] == "TW"){
            holder.ivDevice.setBackgroundResource(R.drawable.ic_tw)

        }


        holder.tvDeviceName.text = Devices.deviceName[position]
        holder.tvDeviceIP.text = Devices.deviceIP[position]
        holder.switchDevice.isChecked = Devices.switch[position]

        holder.layoutDevice.setOnClickListener(){

            showEditDialog(position)

        }

        holder.layoutDevice.setOnLongClickListener {
            val vibe: Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(100)
            showDeleteDialog(position)
        }

        holder.switchDevice.setOnClickListener {

            Devices.switch[position] = holder.switchDevice.isChecked
            //Devices.deviceName[position] = holder.tvDeviceName.text as String
            //Devices.deviceIP[position] = holder.tvDeviceIP.text as String

        }

        //Devices.switch[position] = holder.switchDevice.isChecked

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.device_item, parent, false))
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //View.OnClickListener {
        // Holds the TextView that will add each animal to
        val tvDeviceName: TextView = view.tv_device_name
        val tvDeviceIP: TextView = view.tv_device_ip
        val switchDevice: Switch = view.switch_device
        val layoutDevice: LinearLayout = view.layout_anim
        val ivDevice: ImageView = view.iv_device



        init{
            layoutDevice.setOnClickListener {

            }
        }
        //override fun onClick(view: View) {

            //Devices.switch[position] = switchDevice.isChecked
        //}
        //val clickListener = view.setOnClickListener { clickListener(part)}
    }
    fun showEditDialog(position: Int){
        val dialog = context.let { Dialog(it) }
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.add_device)
        //dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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

        dialogName.setText(Devices.deviceName[position])
        dialogIp.setText(Devices.deviceIP[position])
        var mode = Devices.mode[position]
        var state = Devices.switch[position]

        when {
            Devices.mode[position] == "RGB" -> {
                swRGB.isChecked = true
            }

            Devices.mode[position] == "TW" -> {
                swTW.isChecked = true

            }

            else -> {

            }
        }


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
            if (dialogName.text.toString() != "" && dialogIp.text.toString() != "" && mode != ""){
                Devices.removeData(position)
                Devices.addDataPosition(
                    dialogName.text.toString(),
                    dialogIp.text.toString(),
                    mode,
                    state,
                    position
                )
                notifyDataSetChanged()


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



    }

    private fun showDeleteDialog(position: Int): Boolean {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.remove_item)
        //dialog.window!!.setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //dialog.window.setLayout(600, 400);

        val dialogDelete = dialog.findViewById(R.id.dialog_tv_delete) as TextView


        val yesBtn = dialog .findViewById(R.id.dialog_yes_btn) as Button
        val noBtn = dialog .findViewById(R.id.dialog_no_btn) as Button
        //dialogName.requestFocus()

        yesBtn.setOnClickListener {
            Devices.deviceName.removeAt(position)
            Devices.deviceIP.removeAt(position)
            Devices.switch.removeAt(position)
            notifyDataSetChanged()
            dialog.cancel()

        }
        noBtn.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()
        return true
    }

}
