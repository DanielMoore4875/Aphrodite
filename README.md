# Aphrodite

## Group Members

- Daniel Moore (n01354875)
- Alyssa Gomez (n01042777)
- Jose Antonio Teodoro (n01384776)
- Ryan Black (n01305403)

## Proposal
### Final Project (Sem 6) Hardware/Software Requirements
- Raspberry Pi 4 (Raspberry Pi OS)
    - [Magic Mirror](https://magicmirror.builders/)
- Arduino Uno Rev3
    - Custom PCB (Designed in CENG317 Hardware Production Technology)
        - [DHT22 AM2302 Temp/Humidity Sensor](https://www.amazon.ca/gp/product/B07CM2VLBK)
        - [Voice Recognition Module V3](https://www.aliexpress.com/item/32795424471.html)
        - [PIR Motion Sensor](https://www.amazon.ca/gp/product/B088LR9KRP/)
        - [APA102C LED Stick](https://www.sparkfun.com/products/18354)
        - [Bi-Directional Logic Level Converter](https://www.sparkfun.com/products/12009)
- Google Firebase Database
- 21.5" Monitor (For PI display)
### Component Overview
| Component Name | Component Purpose |
| --------------- | --------------- |
| 21.5" Monitor | 🠊 Plugged into Raspberry Pi to display the mirror dashboard |
| Raspberry Pi 4 | 🠊 Running the Magic Mirror Dashboard</br>🠊 Read the sensor info from the Arduino over I2C (USB connection) |
| Magic Mirror Software | 🠊 Loading the dashboard based on the layout set in the app |
| Arduino Uno Rev 3 | 🠊 Reading sensor data from modules and outputting it to be read by the Pi |
| Firebase Realtime DB | 🠊 For storing user accounts and settings including:</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Name</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Email</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Layouts</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Which voice commands have been set|
| Android App | 🠊 For creating an account which stores user layouts and voice commands</br>🠊 Each user can:</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Create and edit layouts</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Set a layout for the mirror to display</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Setup and change the word for each voice command</br>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;🢧 Terminate their account (Removes all user data from the DB) |

### Software: Android App (AphroditeApp) CENG322 Software Project
&emsp;&emsp;Purpose: For changing which layout is displayed on the mirror, and setting the voice commands.
</br></br>
#### Login Page
&emsp;&emsp;On first launch the user will be sent here and prompted to _Sign in with email_ or _Sign in with Google_.</br>&emsp;&emsp;If the user stays signed in and closes the app, it will auto-login the user on the next launch. This is handled by</br>&emsp;&emsp;Firebase Authentication.
</br></br>
#### Home Page
&emsp;&emsp;On first launch, it shows the default layouts that the user can choose to change the mirror display to. These default</br>&emsp;&emsp;layouts cannot be edited. After first launch, it shows the default and user created layouts (if any). These layouts are</br>&emsp;&emsp;retrieved dynamically from the database when the page loads. Tapping on these layouts will set the mirror display to</br>&emsp;&emsp;that layout. There is an _Add Layout_ button that will send the user to the _**Create/Edit Layouts Page**_
</br></br>
#### Choose Layout to Edit Page
&emsp;&emsp;It shows the user created layouts similar to the _**Home Page**_.  These layouts are retrieved dynamically from the</br>&emsp;&emsp;database when the page loads.Tapping on a layout will open the _**Create/Edit Layouts**_ page.
</br></br>
#### Create/Edit Layouts Page
&emsp;&emsp;A TextView for the Layout Name.</br>
&emsp;&emsp;There is a list of modules that the user can turn on or off to have them shown on the mirror.</br>
&emsp;&emsp;If the module is on, the dropdown (spinner) will be enabled and the user can choose a location for that module.</br>
&emsp;&emsp;If the module is off, the dropdown (spinner) will be disabled and greyed out.</br>
&emsp;&emsp;**\*** The Magic Mirror software handles two or more modules in the same location.</br>

&emsp;&emsp;Coming from the _**Home Page**_ _Add Layout_ button</br>
&emsp;&emsp;&emsp;&emsp;Every module will be set to the default position "off" and every dropdown will be disabled (greyed out).</br>

&emsp;&emsp;Taping on one of the layouts on the _**Choose Layout to Edit Page**_</br>
&emsp;&emsp;&emsp;&emsp;The data for each module and its location will be retrieved from Firebase. The layout name will be populated in the</br>&emsp;&emsp;&emsp;&emsp;Layout Name TextView.
</br></br>
#### Account & Settings Page
&emsp;&emsp;_About us_ button shows some information about the project and the company email.</br>
&emsp;&emsp;_Review_ button lets the user share a 0-5 star review with some optional text. This will be stored in Firebase.</br>
&emsp;&emsp;_Settings_ button lets the user logout or terminate their account. (Terminating will remove all data associated with the user</br>&emsp;&emsp;from Firebase. If someone logs in with the same email in the future, it will create a new account since there is no record</br>&emsp;&emsp;of the user.)
</br></br>
#### Voice Command & LED Edit Page
&emsp;&emsp;_Mic muted/un-muted_ button will disable the voice recognition module on the mirror. There may be a cooldown from muting</br>&emsp;&emsp;and ummuting the mic.</br>
&emsp;&emsp;_LED Colour_ button allows the user to change the LED lights that will be on the final mirror.</br>
&emsp;&emsp;The RGB value is stored in Firebase.
</br></br>

