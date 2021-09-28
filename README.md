# Aphrodite
### Daniel Moore (n01354875) Alyssa Gomez (n01042777) Jose Antonio Teodoro (n01384776) Ryan Black (n01305403)

## Proposal
###Hardware
The main computer side hardware for our project will consist of 3 core components: Raspberry Pi 4, a 21.5" monitor and our custom PCB board. Other hardware will include a 2-way mirror, frame, and LED lights. The Raspberry Pi 4 will be used as the main hub for connecting our pcb as well as hosting the software that will be displayed onto our monitor. The monitor, a 21.5” repurposed ASUS monitor will be used to display the app-customizable dashboard that we will create from the software hosted on the Pi. The custom PCB will include all our sensors (voice recognition, temperature, humidity and proximity) and be plugged into the Raspberry Pi to send the readings to be processed and displayed. Additionally, LED lights will be added around the frame to give the user customizable lighting.

###Software
The Raspberry Pi will be the main CPU for our project and will be running the dashboard software, MagicMirror. This software is an open source modular dashboard platform that uses the JavaScript scripting language. The Android app will be used to customize the layout of the mirror dashboard and save multiple user layout profiles in the cloud. We will be developing an interface to communicate the changes from the app to the Raspberry Pi dashboard software using SSH or a similar technology.
