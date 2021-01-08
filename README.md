# LUMINA

This is my exam project at the lbsfe1 in my apprenticeship as electronics technician. I built it to learn basic app development in Kotlin and connect this with my knowledge in electronics. Because I had no experience in app development this project was a learning experience for me and was not built for commercial use.

This LED control app works with an ESP8266 Module the colors are transmitted via the rgb values in the url in addition you can add mode, frequency and brightness.

request example: 192.168.137.122/?r255g255b255m10d10l255&


MainActivity/NavigationDrawer

this Activity handles the navigation between the 4 Fragments
it also contains the holder for the fragments and a drawer layout that contains the app name,
logo and a recycler view with the devices

![alt text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132338_COLORRIZE_edited.jpg)


Fragment_1

this is the RGB Fragment contains a colorPicker with grey selector wheel
the selected is sent to the ESP8266 Module via loading the url with the ip and selected rgb values

![alt text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132345_COLORRIZE_edited.jpg)


Fragment_2

this is the Tunable White fragment that contains two seekBars that control
Tunable White value and brightness both of them send the rgb values to the selected ip

![alt text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132354_COLORRIZE_edited.jpg)


Fragment_3

this is the Animations Fragment it contains a recyclerView
and a floatinActionButton to add items to the recyclerView to
delete items there is a onSwipeListener in the Adapter

![alt text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/Screenshot_20200130-132405_COLORRIZE_edited.jpg)







LED MODUL (LED-M4)

Das LED Modul gibt es als RGB und Tunable White modul. Wir haben mehrere Dauertests und Tests mit der               Wärmebildkamera durchgeführt um eine optimale Hitzeabfuhr zu garantieren.
  
![alt_text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/WaermeLeds.jpg)
  
  
STEUERMODUL (Main_2)

Das Steuermodul dient zur Kommunikation zwischen der App und den LED's. Nach einmaligem Anpassen des Gleichrichters konnten wir bei diesem Modul wie hier zu sehen auch eine gute Hitzeabfuhr garantieren.

![alt_text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/WaermeMain.jpg)


GEHÄUSE (Gehäuse_1.0)

Das Gehäuse wurde mit Hilfe von Johannes Juritsch erstellt. Es kann komplett bis auf die Schrauben aus dem 3D-Drucker gedruckt werden und hat einen modularen Aufbau. Der obere Teil kann abgenommen werden und entweder ein Tunable White oder RGB Modul aufgesteckt werden.

![alt_text](https://raw.githubusercontent.com/BenjaminFlatz/lumina/master/images/purple.jpg)
