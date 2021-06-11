package com.example.e4.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.anim_item.view.*
import androidx.lifecycle.ViewModel
import android.os.Vibrator
import com.example.e4.models.Animations
import com.example.e4.R
import com.example.e4.models.Connection


@Suppress("DEPRECATION")
class AnimAdapter(val context: Context)  : RecyclerView.Adapter<AnimAdapter.ViewHolder>() {
    var mSelectedItem = -1
    var r = 0
    var g = 0
    var b = 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvAnimName.text = Animations.animName[position]
        holder.tvAnimInfo.text = ("m: ${Animations.mode[position]},  f: ${Animations.frequency[position]}Hz,  b: ${Animations.brightness[position]}")

        holder.swAnimPlay?.isChecked = Animations.switch[position]

        holder.layoutAnim?.setOnClickListener(){

            showEditDialog(position)

        }
        holder.layoutAnim?.setOnLongClickListener {

            val vibe:Vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibe.vibrate(100)
            showDeleteDialog(position)
        }

        holder.swAnimPlay?.setOnClickListener {

            Animations.switch[position] = holder.swAnimPlay.isChecked
            //Devices.deviceName[position] = holder.tvDeviceName.text as String
            //Data.devices.ip[position] = holder.tvDeviceIP.text as String

        }
        when {
            Animations.mode[position] == "sine" -> {
                holder.ivAnim.setBackgroundResource(R.drawable.ic_sine)
            }

            Animations.mode[position] == "rect" -> {
                holder.ivAnim.setBackgroundResource(R.drawable.ic_rect)

            }
            Animations.mode[position] == "tri" -> {
                holder.ivAnim.setBackgroundResource(R.drawable.ic_tri)

            }
            else -> {

            }
        }

        holder.swAnimPlay?.setOnCheckedChangeListener { _: CompoundButton, _: Boolean ->
            when {
                Animations.mode[position] == "sine" -> {
                    //controlLeds(10, Animations.frequency[position], Animations.brightness[position])
                }

                Animations.mode[position] == "rect" -> {
                    //controlLeds(20, Animations.frequency[position], Animations.brightness[position])

                }
                Animations.mode[position] == "tri" -> {
                    //controlLeds(30, Animations.frequency[position], Animations.brightness[position])

                }
                else -> {

                }
            }
        }

    }



    override fun getItemCount(): Int {
        return Animations.animName.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.anim_item, parent, false))
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //View.OnClickListener {
        // Holds the TextView that will add each animal to
        val layoutAnim: ConstraintLayout? = view.layout_anim
        val tvAnimInfo: TextView = view.tv_anim_info
        val tvAnimName: TextView = view.tv_anim_name
        val ivAnim: ImageView = view.iv_anim
        val swAnimPlay: Switch? = view.sw_anim_play



    }


    fun controlLeds(mode: Int, frequency: Int, brightness: Int){

        var color = Color.rgb(r,g,b)
        Connection.post_color(color)

    }
    public fun updateData(viewModels: ArrayList<ViewModel>) {
        notifyDataSetChanged()
    }
    private fun showEditDialog(position: Int){
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

        etName.setText(Animations.animName[position])
        mode = Animations.mode[position]
        sbFrequency.progress = Animations.frequency[position]
        sbBrightness.progress = Animations.brightness[position]
        var state = Animations.switch[position]

        etName.isFocusableInTouchMode = true


        when {
            Animations.mode[position] == "sine" -> {
                swSine.isChecked = true
            }

            Animations.mode[position] == "rect" -> {
                swRect.isChecked = true
            }
            Animations.mode[position] == "tri" -> {
                swTri.isChecked = true

            }
            else -> {

            }
        }

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
                Animations.removeData(position)

                Animations.addDataPosition(
                    etName.text.toString(),
                    mode,
                    sbFrequency.progress,
                    sbBrightness.progress,
                    state,
                    position
                )
                notifyDataSetChanged()

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
            Animations.animName.removeAt(position)
            Animations.mode.removeAt(position)
            Animations.frequency.removeAt(position)
            Animations.brightness.removeAt(position)

            notifyDataSetChanged()
            dialog.cancel()
            true

        }
        noBtn.setOnClickListener {
            dialog.cancel()
            false
        }
        dialog.show()
        return false
    }


}
