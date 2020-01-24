# LUMINA
This is my 4th class Project in my apprenticeship as electronics technician. 
This LED control app works with an ESP8266 Module the colors are transmitted via the rgb values in the url ex. 192.168.43.159/r120g10b5 in addition you can add mode, frequency and brightness. I've rebuilt the ESP code from https://randomnerdtver/#more-83277utorials.com/esp32-esp8266-rgb-led-strip-web-ser, you can use the original code to but only the transmission of the rgb values work because modes were implemented by me.

request example: 192.168.137.122/?r255g255b255m10d10l255&


MainActivity/NavigationDrawer

this Activity handles the navigation between the 4 Fragments
it also contains the holder for the fragments and a drawer layout that contains the app name,
logo and a recycler view with the devices
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151202_LUMINA.jpg)hub.com/wrusl/lumina/blob/master/Screenshot_20200124-151130_LUMINA.jpg)


Fragment_1

this is the RGB Fragment contains a colorPicker with grey selector wheel
the selected is sent to the ESP8266 Module via loading the url with the ip and selected rgb values
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151127_LUMINA.jpg)


Fragment_2

this is the Tunable White fragment that contains two seekBars that control
Tunable White value and brightness both of them send the rgb values to the selected ip
![alt text](https://git


Fragment_3

this is the Animations Fragment it contains a recyclerView
and a floatinActionButton to add items to the recyclerView to
delete items there is a onSwipeListener in the Adapter
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151138_LUMINA.jpg)


AddAnimationsDialog

The AddAnimDialog is for creating a new animation or edit an existing one. I'ts a function in the Fragment_3.
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151151_LUMINA.jpg)



app
    alltypes
        class AnimAdapter : Adapter<ViewHolder>
            AnimAdapter(context: Context, wvAnim: MutableList<WebView>)
            inner class ViewHolder : ViewHolder
                ViewHolder(view: View)
                val ivAnim: ImageView
                val layoutAnim: ConstraintLayout?
                val tvAnimInfo: TextView
                val tvAnimName: TextView
            var b: Int
            val context: Context
            var g: Int
            fun getItemCount(): Int
            var mSelectedItem: Int
            fun onBindViewHolder(holder: ViewHolder, position: Int): Unit
            fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            var r: Int
            var wvAnim: MutableList<WebView>
        object Animations
            fun addData(name: String, mode: String, frequency: Int, brightness: Int, state: Boolean): Unit
            fun addDataPosition(name: String, mode: String, frequency: Int, brightness: Int, state: Boolean, position: Int): Unit
            fun add_arguments(): Unit
            var animName: MutableList<String>
            var brightness: MutableList<Int>
            var duration: MutableList<Int>
            var mode: MutableList<String>
            fun removeData(position: Int): Unit
            var switch: MutableList<Boolean>
        class BuildConfig
            BuildConfig()
            static val APPLICATION_ID: String
            static val BUILD_TYPE: String
            static val DEBUG: Boolean
            static val FLAVOR: String
            static val VERSION_CODE: Int
            static val VERSION_NAME: String
        class BuildConfig
            BuildConfig()
            static val APPLICATION_ID: String
            static val BUILD_TYPE: String
            static val DEBUG: Boolean
            static val FLAVOR: String
            static val VERSION_CODE: Int
            static val VERSION_NAME: String
        class DeviceAdapter : Adapter<ViewHolder>
            DeviceAdapter(context: Context, wvDevice: MutableList<WebView>)
            inner class ViewHolder : ViewHolder
                ViewHolder(view: View)
                val ivDevice: ImageView
                val layoutDevice: LinearLayout
                val layoutIvDevice: ImageView?
                val tvDeviceIP: TextView
                val tvDeviceName: TextView
            val context: Context
            fun getItemCount(): Int
            fun onBindViewHolder(holder: ViewHolder, position: Int): Unit
            fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            fun showEditDialog(position: Int): Unit
            var wvDevice: MutableList<WebView>
        object Devices
            fun addData(name: String, ip: String, mode: String, state: Boolean): Unit
            fun addDataPosition(name: String, ip: String, mode: String, state: Boolean, position: Int): Unit
            fun addNames(): Unit
            var b: MutableList<String>
            var deviceIP: MutableList<String>
            var deviceName: MutableList<String>
            var g: MutableList<String>
            fun getIp(i: Int): String
            fun getName(i: Int): String
            fun getState(i: Int): Boolean
            var mode: MutableList<String>
            var r: MutableList<String>
            fun removeData(position: Int): Unit
            var switch: MutableList<Boolean>
        class FragmentStateHelper
            FragmentStateHelper(fragmentManager: FragmentManager)
            val fragmentManager: FragmentManager
            fun restoreState(fragment: Fragment, key: String): Unit
            fun saveState(fragment: Fragment, key: String): Unit
        class Fragment_1 : Fragment
            Fragment_1()
            var b: Int
            var brightness: Int
            var g: Int
            fun onCreate(savedInstanceState: Bundle?): Unit
            fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            var r: Int
        class Fragment_2 : Fragment
            Fragment_2()
            var b: Int
            var brightness: Int
            var g: Int
            fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            fun onDestroyView(): Unit
            fun onSaveInstanceState(outState: Bundle): Unit
            var progressReal: Int
            var r: Int
        class Fragment_3 : Fragment
            Fragment_3()
            fun onActivityCreated(savedInstanceState: Bundle?): Unit
            fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            var recyclerView: RecyclerView?
        class Fragment_4 : Fragment
            Fragment_4()
            fun getMode(ip: String): String
            var isFabOpen: Boolean
            fun onActivityCreated(savedInstanceState: Bundle?): Unit
            fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Unit
            fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            var recyclerView: RecyclerView?
        class HTMLPageDownloader : AsyncTask<Void, Void, String>
            HTMLPageDownloader(link: String, action: String)
            interface HTMLPageDownloaderListener
                abstract fun completionCallBack(html: String): Unit
            var action: String
            protected fun doInBackground(vararg params: Void): String?
            fun getMode(html: String): Unit
            fun getRGBMDL(htmlString: String): Unit
            var link: String
            protected fun onPostExecute(result: String): Unit
        class MainActivity : AppCompatActivity, OnClickListener
            MainActivity()
            lateinit var drawerLayout: DrawerLayout
            var isFabOpen: Boolean
            lateinit var navView: NavigationView
            protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Unit
            fun onClick(v: View?): Unit
            protected fun onCreate(savedInstanceState: Bundle?): Unit
            protected fun onDestroy(): Unit
            lateinit var recyclerView: RecyclerView
            fun updateDeviceMode(): Unit
        open class NetworkSniffTask : AsyncTask<Void, Void, Void>, Parcelable
            NetworkSniffTask(parcel: Parcel)
            NetworkSniffTask()
            companion object CREATOR : Creator<NetworkSniffTask>
                fun createFromParcel(parcel: Parcel): NetworkSniffTask
                fun newArray(size: Int): Array<NetworkSniffTask?>
            fun NetworkSniffTask(context: Context): Unit
            fun checkESP(mac: String): Boolean
            fun checkMatching(ip: String): Boolean
            fun createFromParcel(parcel: Parcel): NetworkSniffTask
            open fun describeContents(): Int
            protected open fun doInBackground(vararg params: Void?): Void?
            fun getMacAddress(ip: String): String?
            fun newArray(size: Int): Array<NetworkSniffTask?>
            fun readARP(): Unit?
            open fun writeToParcel(parcel: Parcel, flags: Int): Unit
