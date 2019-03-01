# Build Instructions for Greenhouse Monitoring System

## Table of Contents
1. [Introduction](#introduction)
2. [System Diagram](#system-diagram)
3. [Material Requirements and Budget](#material-requirements)
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

### Material Requirements

### Time Commitment

### Raspberry Pi Configuration

### Mechanical Assembly

### Soldering

### I2C Detection
To check if the board is functioning and detecting the sensor, open terminal and type
```
sudo i2cdetect -y 1
```
This will display an output of the sensor's address - 0x5B. See sample output below:
![Image of i2cdetection]()

### Power Up

### Unit Testing

### Production Testing

### Reproducible

### Explore with your Sensor
AM2315 - Temperature/Humidity
</br>CCS811 - VOC
</br>SSD1306 - OLED
