package com.example.e4


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e4.ui.Fragment_1
import com.example.e4.ui.Fragment_2
import com.example.e4.ui.Fragment_3
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Window
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.e4.models.Animations
import com.example.e4.models.Devices
import com.example.e4.ui.Fragment_4

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


    // val fabLayout = findViewById<LinearLayout>(R.id.fabLayout)
    // val fabLayout_1 = findViewById<LinearLayout>(R.id.fabLayout_1)
    // val fabLayout_2 = findViewById<LinearLayout>(R.id.fabLayout_2)
    // val fabLayout_3 = findViewById<LinearLayout>(R.id.fabLayout_3)
    //
    // val fab = findViewById<FloatingActionButton>(R.id.fab)
    // val fab1 = findViewById<FloatingActionButton>(R.id.fab_1)
    // val fab2 = findViewById<FloatingActionButton>(R.id.fab_2)
    // val fab3 = findViewById<FloatingActionButton>(R.id.fab_3)
    // var isFABOpen = false
    //
    //
    // val fabRGB  = findViewById<FloatingActionButton>(R.id.fab_rgb)
    // val fabTW = findViewById<FloatingActionButton>(R.id.fab_tw)
    // val fabAnim = findViewById<FloatingActionButton>(R.id.fab_anim)
    // val fabMenu = findViewById<FloatingActionButton>(R.id.fab_menu)
    // var isFABMenuOpen = false


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val toolbar = findViewById<Toolbar>(R.id.toolbar_main)
        //setSupportActionBar(toolbar_main)

        /*val spinnerMain = findViewById<Spinner>(R.id.spinner_main)
        val menuItems = resources.getStringArray(R.menu.bottom_nav_menu)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.menu.bottom_nav_menu,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMain.adapter = adapter*/

        Devices.addNames()
        Animations.add_arguments()

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_container, Fragment_1()).commit()
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        // Setup drawer view

        /*
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Home"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Sport"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Movie"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = PagerAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

        tv_fab1.visibility = View.GONE
        tv_fab2.visibility = View.GONE
        tv_fab3.visibility = View.GONE
*/
        /*
        tv_rgb.visibility = View.GONE
        tv_tw.visibility = View.GONE
        tv_anim.visibility = View.GONE


        fab_rgb.setOnClickListener {
            fragmentTransaction.replace(R.id.frame_container, Fragment_1())
            fragmentTransaction.commit()
            /*
            val intent = Intent(this@MainActivity, RGBActivity::class.java)
            startActivity(intent)*/

        }
        fab_tw.setOnClickListener {
            fragmentTransaction.replace(R.id.frame_container, Fragment_2())
            fragmentTransaction.commit()
            /*
            val intent = Intent(this@MainActivity, TWActivity::class.java)
            startActivity(intent)*/

        }
        fab_anim.setOnClickListener {
            fragmentTransaction.replace(R.id.frame_container, Fragment_3())
            fragmentTransaction.commit()
            /*
            val intent = Intent(this@MainActivity, AnimActivity::class.java)
            startActivity(intent)*/

        }

        fab_menu.setOnClickListener {
            if (!isFabRightOpen) {
                showFABMenuLeft()

            } else {
                closeFABMenuLeft()
            }

        }



        fab_3.setOnClickListener {
            run {
                IntentIntegrator(this@MainActivity).initiateScan()
            }

        }
        fab_2.setOnClickListener {
            val intent = Intent(this@MainActivity, DeviceActivity::class.java)
            startActivity(intent)

        }



        fab.setOnClickListener {
            if (!isFabLeftOpen) {
                showFABMenuRight()

            } else {
                closeFABMenuRight()
            }
        }


    }
*/

        fun showAddDialog() {
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



            swSine.setOnClickListener {
                if (swSine.isChecked) {
                    swRect.isChecked = false
                    swTri.isChecked = false
                    mode = "sine"

                }

            }
        }/*
    private fun showFABMenuRight(){

        isFabLeftOpen = true
        tv_fab1.visibility = View.VISIBLE
        tv_fab2.visibility = View.VISIBLE
        tv_fab3.visibility = View.VISIBLE
        fabLayout_1?.visibility = View.VISIBLE
        fabLayout_2?.visibility = View.VISIBLE
        fabLayout_3?.visibility = View.VISIBLE
        fabLayout?.visibility = View.VISIBLE
        fab?.animate()?.rotationBy(180F)
        fabLayout_1?.animate()?.translationY(-resources.getDimension(R.dimen.standard_55))
        fabLayout_2?.animate()?.translationY(-resources.getDimension(R.dimen.standard_105))
        fabLayout_3?.animate()?.translationY(-resources.getDimension(R.dimen.standard_155))


        //closeFABMenuLeft()


    }
    private fun closeFABMenuRight(){

        isFabLeftOpen = false
        tv_fab1.visibility = View.GONE
        tv_fab2.visibility = View.GONE
        tv_fab3.visibility = View.GONE
        fab?.animate()?.rotation(0F)
        fabLayout_1?.animate()?.translationY(0F)
        fabLayout_2?.animate()?.translationY(0F)
        fabLayout_3?.animate()?.translationY(0F)
        fabLayout_3?.animate()?.translationY(0F)?.setListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }

            override fun onAnimationEnd(animator: Animator) {
                if (!isFabLeftOpen) {
                    fabLayout_1?.visibility = View.GONE
                    fabLayout_2?.visibility = View.GONE
                    fabLayout_3?.visibility = View.GONE
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
    /*
    private fun showFABMenuLeft() {
        isFabRightOpen = true
        tv_rgb.visibility = View.VISIBLE
        tv_tw.visibility = View.VISIBLE
        tv_anim.visibility = View.VISIBLE
        layoutRGB?.visibility = View.VISIBLE
        layoutTW?.visibility = View.VISIBLE
        layoutAnim?.visibility = View.VISIBLE
        layoutMenu?.visibility = View.VISIBLE
        fab_menu?.animate()?.rotationBy(180F)
        layoutRGB?.animate()?.translationX(resources.getDimension(R.dimen.standard_55))
        layoutRGB?.animate()?.translationY(-resources.getDimension(R.dimen.standard_105))
        layoutTW?.animate()?.translationX(resources.getDimension(R.dimen.standard_105))
        layoutAnim?.animate()?.translationX(resources.getDimension(R.dimen.standard_55))
        layoutAnim?.animate()?.translationY(resources.getDimension(R.dimen.standard_105))


        closeFABMenuRight()

    }

    private fun closeFABMenuLeft() {
        isFabRightOpen = false
        tv_rgb.visibility = View.GONE
        tv_tw.visibility = View.GONE
        tv_anim.visibility = View.GONE
        fab_menu?.animate()?.rotation(0F)
        layoutRGB?.animate()?.translationX(0F)
        layoutRGB?.animate()?.translationY(0F)

        layoutTW?.animate()?.translationX(0F)
        layoutTW?.animate()?.translationY(0F)

        layoutAnim?.animate()?.translationX(0F)
        layoutAnim?.animate()?.translationY(0F)
        layoutAnim?.animate()?.translationX(0F)
            ?.setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    if (!isFabRightOpen) {
                        layoutRGB?.visibility = View.GONE
                        layoutTW?.visibility = View.GONE
                        layoutAnim?.visibility = View.GONE
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

    }*/
    override fun onBackPressed() {
        if (isFabLeftOpen) {
            closeFABMenuRight()
            //closeFABMenuLeft()
        } else {
            super.onBackPressed()
        }
    }*/

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            var result: IntentResult? =
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

            if (result != null) {

                if (result.contents != null) {
                    Toast.makeText(this@MainActivity, "${result.contents}", Toast.LENGTH_SHORT)
                        .show()
                    //Devices.addData()

                } else {
                    Toast.makeText(this@MainActivity, "scan failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }

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




