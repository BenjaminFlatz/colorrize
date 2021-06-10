package com.example.colorrize


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.JsonWriter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.colorrize.ui.Fragment_1
import com.example.colorrize.ui.Fragment_2
import com.example.colorrize.ui.Fragment_3
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.colorrize.models.Animations
import com.example.colorrize.models.Data
import com.example.colorrize.ui.Fragment_4

/**
 * this Activity handles the navigation between the 4 Fragments
 * it also contains the holder for the fragments
 *
 */
class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val ADD_TASK_REQUEST = 1
    var isFabRightOpen = false
    var isFabLeftOpen = false



    //val fm = supportFragmentManager
    //val fragmentTransaction = fm.beginTransaction()
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, Fragment_1()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, Fragment_2()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_3 -> {
                    /*val intent = Intent(this@MainActivity, AnimActivity::class.java)
                    startActivity(intent)*/
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, Fragment_3()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_4 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, Fragment_4()).commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    constructor(parcel: Parcel) : this() {
        isFabRightOpen = parcel.readByte() != 0.toByte()
        isFabLeftOpen = parcel.readByte() != 0.toByte()
    }


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Devices.addNames()
        Animations.add_arguments()

        supportFragmentManager.beginTransaction().add(R.id.frame_container, Fragment_1()).commit()
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Data.readDevices("devices.json")

    }

    override fun onDestroy() {

        super.onDestroy()
        Data.writeDevices("devices.json")


    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isFabRightOpen) 1 else 0)
        parcel.writeByte(if (isFabLeftOpen) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }


}




