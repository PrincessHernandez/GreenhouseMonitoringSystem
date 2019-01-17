---
tagline: Website with GitHub Pages
description: Simple website with GitHub Pages
layout: page
title: VOC Sensor Project Blog
---

# CENG317 VOC Sensor Project

### Week 15 December 11, 2018
* The build instructions is due today. I have included all the requirements and instructions for the project. Click [here](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/README.md) for the instructions.

### Week 13 November 27, 2018
* Today is my [presentation](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Presentation/VOC%20Sensor.pptx).
* I started a rough copy of my build instructions.

### Week 12 November 20, 2018
* Project enclosure is due today.
* The [design](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Project%20Enclosure/Pi2Case.pdf) of the enclosure is inspired by the [template](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Project%20Enclosure/Template/Pi2CaseX6.pdf) created for reference purposes. I increased the height by 0.25" and I added a cutout of where the sensor would position at the top of the enclosure. I also added a cover for the cutout when the Pi is not in used.
* The following pictures below is the final project enclosure:

<img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/Enclosure1.JPG" width="350"><img src="https://raw.githubusercontent.com/PrincessHernandez/VOC_Sensor/master/images/Enclosure2.JPG" width="350">

### Week 11 November 13, 2018 - Milestone
* PCB Power Up is due today. 
* CCS811 is working as intended. I will be modifying the [code](https://github.com/PrincessHernandez/VOC_Sensor/tree/master/documentation/Code) to get rid of temperatue/humidity compensation because I don't need it in my project. Apart from that, it detects levels of ECO2 and TVOC which are both mentioned in my [proposal](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/ProposalContentPrincessRev02.xlsx). The following images are samples of output detected from CCS811.

![Image of Output1](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/SampleOutput2.PNG?raw=true)
![Image of Output2](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/SampleOutput1.PNG?raw=true)
* I also started designing the case with the help of the [template](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Project%20Enclosure/Template/Pi2CaseX6.pdf) provided for us.

#### Update
* The code is now modified (Nov 26).

### Week 10 November 6, 2018
* PCB milestone is due today. I have yet to solder the 7 pin socket.
* I am currently behind my [project schedule](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/ProjectSchedule.mpp).
* I soldered my PCB on Nov 5. I am still waiting for the 7 pin socket I ordered online. I found out that Canada Post is on strike, so I will wait for my order to arrive by the end of this week. Otherwise I will buy the socket before Friday to finish soldering.
* I got a 5 pin socket from the Prototype Lab and used it as an alternate socket for my sensor since I only need 5 pins from my sensor.

![Image of PCB Soldered](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/SolderedPCB.PNG?raw=true)

### Week 9 October 30, 2018
* I designed my PCB through [Fritzing](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Fritzing%20CCS811/VOC-CCS311-Princess.fzz). The fritzing images are in the following:
     
      Breadboard     
    
![Image of Breadboard](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/VOC-CCS311-Princess_bb.png?raw=truewidth=25)
     
      Schematic
    
![Image of Schematic](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/VOC-CCS311-Princess_schem.png?raw=true)
    
      PCB     
    
![Image of PCB](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/VOC-CCS311-Princess_pcb.png?raw=true)
* I submitted my [Gerber files](https://github.com/PrincessHernandez/VOC_Sensor/tree/master/documentation/Gerber/VOC%20CCS811) to Prototype Lab.
* I updated my project's [budget](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/BudgetUpdated.xlsx) to accommodate for the PCB. 
* I sent my Gerber file again on Nov 2. PCB is expected Nov 5. 

### Week 8 October 23, 2018 - Milestone
* I forgot my Raspberry Pi. 
* Demonstrated wiring on breadboard (borrowed Rasp Pi from Parts Crib).

![Image of Wiring1](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/breadboardSensor(1).PNG?raw=true) ![Image of Wiring2](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/breadboardSensor(2).PNG?raw=true)
* Used i2cdetect command to detect CCS811 sensor on the pi. The result is the correct address (0x5B).

![Image of Sensor Detected](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/i2cDetect.PNG?raw=true)

### Week 7 October 16, 2018
* Pseudo code assignment due.

### Week 6 October 9, 2018 - Study Day
* Soldered my sensor to the header.

![Image of Soldered Sensor](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/solderedSensor.PNG?raw=true)
* Worked on Pseudo code assignment.

### Week 5 October 2, 2018
* I showed proof of ordered parts.
* Setting up Raspberry Pi.

### Week 4 September 25, 2018
* I submitted my [budget](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/Budget.xlsx).
* I ordered my parts on September 27.

![Image of Parts Receipt](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/Ordered%20Parts.png?raw=true)
* I paid for import charges and received my parts on September 28.

![Image of Import Fee Receipt](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/Import%20Fee.PNG?raw=true)

### Week 3 September 18, 2018
* I submitted my [project schedule](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/ProjectSchedule.mpp).
![Image of Project Schedule](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/images/ProjectSchedule.png?raw=true)

### Week 2 September 11, 2018
* I submitted my [proposal](https://github.com/PrincessHernandez/VOC_Sensor/blob/master/documentation/ProposalContentPrincessRev02.xlsx).

### Week 1 September 4, 2018
* I created my [repository](https://github.com/PrincessHernandez/VOC_Sensor) and choose the sensor that I will be building with for my IoT Hardware project.
* The sensor I chose is a VOC sensor.
