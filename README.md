# LUMINA
This is my 4th class Project in my apprenticeship as electronics technician. 
This LED control app works with an ESP8266 Module the colors are transmitted via the rgb values in the url in addition you can add mode, frequency and brightness. I've rebuilt the ESP code from https://randomnerdtver/#more-83277utorials.com/esp32-esp8266-rgb-led-strip-web-ser, you can use the original code too but only the transmission of the rgb values work because modes were implemented by me.

request example: 192.168.137.122/?r255g255b255m10d10l255&


MainActivity/NavigationDrawer

this Activity handles the navigation between the 4 Fragments
it also contains the holder for the fragments and a drawer layout that contains the app name,
logo and a recycler view with the devices

![alt text](https://static.wixstatic.com/media/263f5b_7047135c29a549ddb337e7105b3cd12e~mv2.jpg/v1/fill/w_300,h_576,al_c,q_80,usm_0.66_1.00_0.01/Screenshot_20200130-132338_COLORRIZE_edi.webp)


Fragment_1

this is the RGB Fragment contains a colorPicker with grey selector wheel
the selected is sent to the ESP8266 Module via loading the url with the ip and selected rgb values

![alt text](https://static.wixstatic.com/media/263f5b_7bc38c0fac7249c8ac52508685ba68c7~mv2.jpg/v1/fill/w_300,h_576,al_c,q_80,usm_0.66_1.00_0.01/Screenshot_20200130-132345_COLORRIZE_edited.webp)


Fragment_2

this is the Tunable White fragment that contains two seekBars that control
Tunable White value and brightness both of them send the rgb values to the selected ip

![alt text](https://static.wixstatic.com/media/263f5b_28c68d12a67b4af6aff95c6f189a9de1~mv2.jpg/v1/fill/w_300,h_576,al_c,q_80,usm_0.66_1.00_0.01/Screenshot_20200130-132354_COLORRIZE_edi.webp)


Fragment_3

this is the Animations Fragment it contains a recyclerView
and a floatinActionButton to add items to the recyclerView to
delete items there is a onSwipeListener in the Adapter

![alt text](https://static.wixstatic.com/media/263f5b_cb5fd0317181477d81a2c749b12bdf99~mv2.jpg/v1/fill/w_300,h_576,al_c,q_80,usm_0.66_1.00_0.01/Screenshot_20200130-132405_COLORRIZE_edi.webp)


AddAnimationsDialog

The AddAnimDialog is for creating a new animation or edit an existing one. I'ts a function in the Fragment_3.

![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151151_LUMINA.jpg)
