# Build Instructions for Greenhouse Monitoring System

### Table of Contents
1. [Introduction](#introduction)
2. [System Diagram](#system-diagram)
3. [Material Requirements and Budget](#material-requirements-and-Budget)
4. [Time Commitment](#time-commitment)
5. [Raspberry Pi Configuration](#raspberry-pi-configuration)
6. [Mechanical Assembly](#mechanical-assembly)
7. [Soldering](#soldering)
8. [Power Up](#power-up)
9. [I2C Detection](#i2c-detection)
10. [Unit Testing](#unit-testing)
11. [Production Testing](#production-testing)
12. [Reproducible?](#reproducible)

### Introduction
The Greenhouse Monitoring System is a device that allows greenhouse technicians to monitor atmospheric factors that may affect plant growth. The temp/humid sensor measures temperature and humidity respectively, while the VOC sensor can measure equivalent CO2. These three factors can have an impact in plants whether they are growing at a normal or slow pace. To increase awareness, our monitoring system serves as a tool for greenhouse technicians to maintain and control the temperature, humidity and CO2 levels in a greenhouse environment. 

### System Diagram
![Image of System Diagram](https://github.com/PrincessHernandez/GreenhouseMonitoringSystem/blob/master/images/Sys%20Diagram.png?raw=true)

### Material Requirements and Budget
Description                                                                     |Source      |$CAD Price|$USD Price|Link
--------------------------------------------------------------------------------|:----------:|:--------:|:--------:|------
CanaKit Raspberry Pi 3 B+ Starter Kit                                           | Amazon     | $98.99   |          |https://www.amazon.ca/CanaKit-Raspberry-Starter-Premium-Black/dp/B07BCC8PK7/ref=sr_1_1_sspa?crid=3FN3IC415XF1B&keywords=raspberry+pi+3&qid=1551450371&s=electronics&sprefix=raspberry+%2Celectronics%2C153&sr=1-1-spons&psc=1
OLED Display for Arduino 128x64 Pixel I2C, 0.96 inch, SSD1306, Library, 3-5V    | Amazon     | $6.29    |          | https://www.amazon.ca/Display-Arduino-128x64-SSD1306-Library/dp/B077D4RQG1/ref=sr_1_1_sspa?hvadid=324955435621&hvdev=c&hvlocphy=9000993&hvnetw=g&hvpos=1t1&hvqmt=b&hvrand=7609421822030419687&hvtargid=kwd-45652301614&keywords=oled+ssd1306&qid=1551449756&s=electronics&sr=1-1-spons&tag=googcana-20&psc=1
20x2-pin Header (Female)                                                        | Amazon     | $9.99    |          | https://www.amazon.ca/2x20-pin-Female-Stacking-Header-Raspberry/dp/B071FT161B/ref=sr_1_3?crid=SKA71RFLSHC5&keywords=20+pin+header+female&qid=1551450588&s=electronics&sprefix=20+pin+header%2Celectronics%2C149&sr=1-3
5-pin Header (Female)                                                           | Creatron   | $0.43    |          | https://www.creatroninc.com/product/5-pin-receptacle-socket/?search_query=4+pin+stackable+header&results=48
4-pin Header (Female)                                                           | Creatron   | $0.42    |          | https://www.creatroninc.com/product/4-pin-receptacle-socket/
AM2315 - Encased I2C Temperature/Humidity Sensor                                | Adafruit   |          | $29.95   | https://www.adafruit.com/product/1293
SparkFun Air Quality Breakout - CCS811                                          | Sparkfun   |          | $20.95   | https://www.sparkfun.com/products/14193?_ga=2.97662492.2095878335.1537831851

\*Prices does not include tax and shipping.
                                                       
### Time Commitment
This project can be completed in approximately 7 days if you followed the mechanical assembly and diagrams. Desired to do so, you must order the materials 2 - 3 weeks before working on this project as shipping takes about 1 - 2 weeks. Once you receive the materials, you can start configuring your Raspberry Pi which will take around 3 hours. Connecting the parts to the Raspberry Pi will take 30 minutes. And then to set up the code on the Raspberry Pi and testing the code will take around a 1 hour. It is recommended that you take 3 - 4 hours working on this project daily.

### Raspberry Pi Configuration
1. Create your Raspberry Pi's [image](https://github.com/six0four/StudentSenseHat/blob/master/cribpisdcard.md) for your project.

2. Then test your LED to [blink](https://github.com/six0four/StudentSenseHat/blob/master/README.md#student-raspberry-pi-image-creation-and-test-code).

### Mechanical Assembly
1. Set your sensors onto your breadboard and connect your sensors to the appropriate GPIO pinout of the Raspberry Pi. The wiring should look something like this:
<br/><img src="https://github.com/PrincessHernandez/GreenhouseMonitoringSystem/blob/master/images/Setup.png?raw=true" height="350">
2. Boot your Raspberry Pi and open terminal.
3. To check if your sensors are functioning, click [here](#i2c-detection).

### Soldering
1. You can design your own PCB using [Fritzing software](http://fritzing.org/download/) for free or our version of the [fritzing file](https://github.com/PrincessHernandez/GreenhouseMonitoringSystem/blob/master/documentation/Fritzing/Greenhouse.fzz). You can refer to the images below: 
</br>Breadboard view
<img src="https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/Greenhouse_bb.png" width="650">
<br/>Schematic view
<img src="https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/Greenhouse_schem.png" width="650"> 
<br/>PCB view
<img src="https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/Greenhouse_pcb.png" width="650">
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
	* Please make sure that the WAKE pin is connected to GND. Otherwise, you will not get the address for CCS811 sensor.

3. Once you have obtained your PCB board solder the following:
* Vias\*</br>
![Image of Vias Soldered](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/Vias.png)
<br/>\*Note: You must thread a single strand of wire through the holes, solder it, and then cut the remaining wires off.
* 20x2-pin socket<br/>
![Image of 40 pin Soldered](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/40pin.png)
* 4 and 5-pin sockets <br/>
![Image of sockets Soldered](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/imagehere.png)

Once you have finished soldering, your board should look like this along with the sensor:
<br/>![Image of Board Soldered](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/imagehere.png)

### Power Up
1. Boot your Raspberry Pi and open terminal.
2. To check if your sensors are functioning, click [here](#i2c-detection). If they are not being detected, there might be a problem with the solder or the PCB itself.
3. Download the following codes here to setup your sensors: [CCS811](https://github.com/PrincessHernandez/GreenhouseMonitoringSystem/blob/master/python%20script/classes/CCS811_RPi.py) and [SSD1306]().

### I2C Detection
To check if the board is functioning and detecting the sensor, open terminal and type
```
sudo i2cdetect -y 1
```
This will display an output of the sensor's address - 0x3C, 0x5B. See sample output below:
![Image of i2cdetection](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/i2cdetect.PNG)

To wake up AM2315 Temp/Humid sensor, run the command(quickly) again. It will show all addresses - 0x3C, 0x5B, 0x5C. 
![Image of i2cdetection2](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/i2cdetect2.PNG)

### Unit Testing
Download the program [greenhouse.py](). Now that you've downloaded the codes and detected your sensors, go to terminal and run
```
sudo python greenhouse.py
```
This command should run the program and display on the terminal. An example output is shown below:
![Image of Sample Output Terminal](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/SampleOutput.PNG)

### Production Testing
Now that you've seen what the sensors' output will look like, download the program [greenhouse2.py]() and run
```
sudo python greenhouse2.py
```
This command should run the program and display the output on the OLED screen. An example output is shown below:
![Image of Sample Output OLED](https://raw.githubusercontent.com/PrincessHernandez/GreenhouseMonitoringSystem/master/images/OLED%20Output.png)

### Reproducible
You are now ready to conduct experiments with your device. \*We highly recommend you test in greenhouses for its sole purpose.
