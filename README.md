# Build Instructions for Raspberry Pi VOC Sensor

## Table of Contents
1. [Introduction](#introduction)
2. [Material Requirements](#material-requirements)
3. [Raspberry Pi Configuration](#raspberry-pi-configuration)
4. [Mechanical Assembly and Soldering](#mechanical-assembly-and-soldering)
5. [I2C Detection](#i2c-detection)
6. [Power Up](#power-up)
7. [Explore with your Sensor](#explore-with-your-sensor)

### Introduction
Volatile Organic Compounds (VOC) can be found in everyday products such as cleaning supplies, paint, cosmetic products, fuel, and many more. They have the potential to contaminate our health, so monitoring air quality is very crucial for our everyday means. The CCS811 Air Quality Sensor can sense VOC and CO2, two parameters that are important for monitoring air quality. The following instructions are provided to assist you in developing a VOC sensor that can measure TVOC and eCO2 levels in the air.
<br/><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/Enclosure1.JPG" width="350"></p>

> I recommend that before attempting this build that you have accomplished watching videos on [soldering](https://www.youtube.com/watch?v=BLfXXRfRIzY&list=PLQ32vZrF5U2lFOJTtZDytBWBYVLNp4RYz). 

### Material Requirements
The project's total budget will cost you approx. CAD$232.12 excluding tax and shipping. The prices can be converted depending on where you are from. If you would like to see my personal budget click [here](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/BudgetUpdated.xlsx). Keep in mind that my budget costs lower because I own some of the parts (or had access to in Prototype Lab) from the list.
The following links below are sources of where you can purchase the items:
* [Raspberry Pi 3 Kit](https://www.amazon.ca/CanaKit-Raspberry-Complete-Starter-Kit/dp/B01CCF6V3A/ref=sr_1_5?s=pc&ie=UTF8&qid=1516324581&sr=1-5&keywords=Raspberry+Pi+3) - CAD$99.99
* [CCS811 Air Quality Sensor](https://www.sparkfun.com/products/14193) - CAD$28.05
* [Break Away Headers - Straight](https://www.sparkfun.com/products/116) - CAD$2.01
* [7 Pin Receptacle Socket](https://www.creatroninc.com/product/7-pin-receptacle-socket/) - CAD$0.45
* [40 Pin GPIO Connector Header](https://www.buyapi.ca/product/40-pin-gpio-connector-header/) - CAD$1.95
* [Custom PCB](https://www.pcbway.com/?adwgc=667&campaignid=172480651&adgroupid=8787904531&feeditemid=&targetid=kwd-34746800&loc_physical_ms=9000922&matchtype=p&network=g&device=c&devicemodel=&creative=189085816950&keyword=pcb%20manufacturing&placement=&target=&adposition=1t1&gclid=Cj0KCQiAi57gBRDqARIsABhDSMpsNka-o0C5SQcvMYkiUXbYsOpfyNvY4I17pEzjXb1DlC4_ia_7dHkaAqKGEALw_wcB) - CAD$88.88 
<br/>Depending on the design of your PCB prices may vary. 
<br/>You can design your own PCB using [Fritzing](http://fritzing.org/download/) for free. 
<br/>Check out my design of the [PCB](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Fritzing%20CCS811/VOC-CCS311-Princess.fzz).
* [Safety Glasses](https://www.amazon.ca/3M-Virtua-Glasses-Polycarbonate-Anti-Scratch/dp/B00AEFBLW2/ref=sr_1_6?ie=UTF8&qid=1544063725&sr=8-6&keywords=safety+glasses) - CAD$9.27
* (Optional) [Anti-Static Wrist Strap](https://www.amazon.ca/KingWin-ATS-W24-Anti-Static-Wrist-Strap/dp/B0042TLA90/ref=sr_1_6?ie=UTF8&qid=1544063571&sr=8-6&keywords=anti+static+wrist+strap) - CAD$2.50

### Raspberry Pi Configuration
1. Create your Raspberry Pi's [image](https://github.com/six0four/StudentSenseHat/blob/master/cribpisdcard.md) for your project.

2. Then test your LED to [blink](https://github.com/six0four/StudentSenseHat/blob/master/README.md#student-raspberry-pi-image-creation-and-test-code).

### Mechanical Assembly and Soldering
1. Make sure to break the 7 pins from the Break Away Header. Then start by soldering the sensor to the pins of the header. 
<br/><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/Soldering%20Sensor%20to%20Header.jpg" width="350">

2. You can design your own PCB using [Fritzing software](http://fritzing.org/download/) for free or my version of the [fritzing file](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Fritzing%20CCS811/VOC-CCS311-Princess.fzz). You can refer to the image of the schematic and PCB designs below.
<br/><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/VOC-CCS311-Princess_schem.png" width="350"> <img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/VOC-CCS311-Princess_pcb.png" width="350">
<br/>Here are the following pins that you should know for this project:
##### Power Pins
* Vin - power pin
	* Since the sensor uses 3.3V, give it the same power as the logic level of you Raspberry Pi.
* GND - common ground for power and logic
##### Logic Pins
* SCL - i2c clock pin
	* Connect to your Raspberry Pi i2c clock line.
* SDA - i2c data pin
	* Connect to your Raspberry Pi i2c data line.
* WAKE\* - wakeup pin for the sensor
	* Please make sure that the WAKE pin is connected to GND. Otherwise, you will not get an address.

3. Once you have obtained your PCB board solder the following:
* Vias\*
* 40-pin socket 
* 7-pin socket
<br/>\*Note: You must thread a single strand of wire through the holes, solder it, and then cut the remaining wires off.

Once you have finished soldering, your board should look like this along with the sensor:
<br/><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/SolderedPCB.PNG" width="350">

### I2C Detection
To check if the board is functioning and detecting the sensor, open terminal and type
```
sudo i2cdetect -y 1
```
This will display an output of the sensor's address - 0x5B. See sample output below:
<br/><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/i2cdetect2.jpg" width="350">

### Power Up
To test your sensor download the python code [here](https://github.com/PrincessHernandez/VOC_Sensor/tree/master/documentation/Code). To run the program in the terminal type
```
sudo python ccs811.py
```
`Invalid eCO2 value` detects the CO2 value of greater than what the range should be. You can refer to the sample outputs below. Your output differs depending on your surrounding environment.
<br/><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/SampleOutput3.PNG" width="350"> <img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/SampleOutput4.png" width="350"> 
> **Burn-in Time** - The CCS811 data sheet recommends a burn-in of 48 hours and run-in of 20 minutes. It is important for your sensor to warm up for 20 minutes for a valid output data.

### Explore with your Sensor
You are now ready to conduct experiments with your VOC sensor. 
