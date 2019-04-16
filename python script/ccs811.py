#
# CCS811_RPi class usage example
#
# Petr Lukas
# July, 11 2017
#
# Version 1.0
#
#
## Edited by Princess Hernandez 

import time
from CCS811_RPi import CCS811_RPi
ccs811 = CCS811_RPi()

'''
MEAS MODE REGISTER AND DRIVE MODE CONFIGURATION
0b0       Idle (Measurements are disabled in this mode)
0b10000   Constant power mode, IAQ measurement every second
0b100000  Pulse heating mode IAQ measurement every 10 seconds
0b110000  Low power pulse heating mode IAQ measurement every 60
0b1000000 Constant power mode, sensor measurement every 250ms
'''
# Set MEAS_MODE (measurement interval)
configuration = 0b100000

# Set read interval for retriveving last measurement data from the sensor
pause = 10

print 'Checking hardware ID...'
hwid = ccs811.checkHWID()
if(hwid == hex(129)):
        print 'Hardware ID is correct'
else: print 'Incorrect hardware ID ',hwid, ', should be 0x5B'

ccs811.configureSensor(configuration)
print '---------------------------------'

while(1):
        statusbyte = ccs811.readStatus()
        print 'STATUS: ', bin(statusbyte)

        error = ccs811.checkError(statusbyte)
        if(error):
                print 'ERROR:',ccs811.checkError(statusbyte)
              
        if(not ccs811.checkDataReady(statusbyte)):
                print 'No new samples are ready' 
                print '---------------------------------';
                time.sleep(pause)
                continue;
        result = ccs811.readAlg();
        if(not result):
                #print 'Invalid result received'
                time.sleep(pause)
                continue;
        baseline = ccs811.readBaseline()
        print 'eCO2: ',result['eCO2'],'ppm'
       # print 'TVOC: ',result['TVOC'], 'ppb'
        print '---------------------------------';

        time.sleep(pause)



