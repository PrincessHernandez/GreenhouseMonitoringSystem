# Build Instructions for Greenhouse Monitoring System

## Table of Contents
1. [Introduction](#introduction)
2. [System Diagram](#system-diagram)
3. [Material Requirements and Budget](#material-requirements-and-Budget)
4. [Time Commitment](#time-commitment)
5. [Raspberry Pi Configuration](#raspberry-pi-configuration)
6. [Mechanical Assembly](#mechanical-assembly)
7. [Soldering](#soldering)
8. [I2C Detection](#i2c-detection)
9. [Power Up](#power-up)
10. [Unit Testing](#unit-testing)
11. [Production Testing](#production-testing)
12. [Reproducible?](#reproducible)
13. [Explore with your Sensor](#explore-with-your-sensor)

### Introduction
The Greenhouse Monitoring System is a device that allows greenhouse technicians

### System Diagram
![Image of System Diagram](https://github.com/PrincessHernandez/GreenhouseMonitoringSystem/blob/master/images/Sys%20Diagram.png?raw=true)

### Material Requirements and Budget
Description                                           |Source      |Part #      |$CAD Price|$USD Price|Link
------------------------------------------------------|:----------:|:----------:|:--------:|:--------:|------
SparkFun Air Quality Breakout - CCS811                | Sparkfun   | SEN-14193  |          | $20.95   | https://www.sparkfun.com/products/14193?_ga=2.97662492.2095878335.1537831851

AM2315 - Encased I2C Temperature/Humidity Sensor      |      X     |            |          |          | https://www.adafruit.com/product/1293
                                                      |      X     |            |          |          |
                                                      |      X     |            |          |          | 

### Time Commitment

### Raspberry Pi Configuration

### Mechanical Assembly

### Soldering

### I2C Detection
To check if the board is functioning and detecting the sensor, open terminal and type
```
sudo i2cdetect -y 1
```
This will display an output of the sensor's address - 0x3B, 0x5B. See sample output below:
![Image of i2cdetection]()

To wake up AM2315 Temp/Humid sensor, run the command again. It will show all addresses - 0x3B, 0x5B, 0x5C. 
![Image of i2cdetection2]()

### Power Up

### Unit Testing

### Production Testing

### Reproducible

### Explore with your Sensor
AM2315 - Temperature/Humidity
</br>CCS811 - VOC
</br>SSD1306 - OLED
