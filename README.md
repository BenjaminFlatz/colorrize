# LUMINA

This is my exam project at the lbsfe1 in my apprenticeship as electronics technician. I built it to learn basic app development in Kotlin and connect this with my knowledge in electronics. Because I had no experience in app development this project was a learning experience for me and was not built for commercial use.

This LED control app works with an ESP8266 Module the colors are transmitted via the rgb values in the url in addition you can add mode, frequency and brightness.

request example: 192.168.137.122/?r255g255b255m10d10l255&


MainActivity/NavigationDrawer

this Activity handles the navigation between the 4 Fragments
it also contains the holder for the fragments and a drawer layout that contains the app name,
logo and a recycler view with the devices

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132338_COLORRIZE_edited.jpg" width="300">


Fragment_1

this is the RGB Fragment contains a colorPicker with grey selector wheel
the selected is sent to the ESP8266 Module via loading the url with the ip and selected rgb values

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132345_COLORRIZE_edited.jpg" width="300">


Fragment_2

this is the Tunable White fragment that contains two seekBars that control
Tunable White value and brightness both of them send the rgb values to the selected ip

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132354_COLORRIZE_edited.jpg" width="300">


Fragment_3

this is the Animations Fragment it contains a recyclerView
and a floatinActionButton to add items to the recyclerView to
delete items there is a onSwipeListener in the Adapter

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132405_COLORRIZE_edited.jpg" width="300">







LED unit (LED-M4)

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/WaermeLeds.jpg" width="300">
  
  
Control unit (Main_2)

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/WaermeMain.jpg" width="300">


Case (Gehäuse_1.0)

<img src="https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/purple.jpg" width="300">
