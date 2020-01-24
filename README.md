# LUMINA
This is my 4th class Project in my apprenticeship as electronics technician. 
This LED control app works with an ESP8266 Module the colors are transmitted via the rgb values in the url ex. 192.168.43.159/r120g10b5 in addition you can add mode, frequency and brightness. I've rebuilt the ESP code from https://randomnerdtver/#more-83277utorials.com/esp32-esp8266-rgb-led-strip-web-ser, you can use the original code to but only the transmission of the rgb values work because modes were implemented by me.

request example: 192.168.137.122/?r255g255b255m10d10l255&

Fragment_1

this is the RGB Fragment contains a colorPicker with grey selector wheel
the selected is sent to the ESP8266 Module via loading the url with the ip and selected rgb values
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151127_LUMINA.jpg)


Fragment_2

this is the Tunable White fragment that contains two seekBars that control
Tunable White value and brightness both of them send the rgb values to the selected ip
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151130_LUMINA.jpg)


Fragment_3

this is the Animations Fragment it contains a recyclerView
and a floatinActionButton to add items to the recyclerView to
delete items there is a onSwipeListener in the Adapter
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151138_LUMINA.jpg)


MainActivity/NavigationDrawer

this Activity handles the navigation between the 4 Fragments
it also contains the holder for the fragments and a drawer layout that contains the app name,
logo and a recycler view with the devices
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151202_LUMINA.jpg)


AddAnimationsDialog

The AddAnimDialog is for creating a new animation or edit an existing one. I'ts a function in the Fragment_3.
![alt text](https://github.com/wrusl/lumina/blob/master/Screenshot_20200124-151151_LUMINA.jpg)

