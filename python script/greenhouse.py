import time
from tentacle_pi.AM2315 import AM2315
from CCS811_RPi import CCS811_RPi

am = AM2315(0x5c,"/dev/i2c-1")
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

while(1):
        statusbyte = ccs811.readStatus()

        error = ccs811.checkError(statusbyte)
        if(error):
                print 'ERROR:',ccs811.checkError(statusbyte)

        temperature, humidity, crc_check = am.sense()        
        if(not ccs811.checkDataReady(statusbyte)):
                print 'No new samples are ready'
                print '---------------------------------';
                time.sleep(pause)
                continue;
        result = ccs811.readAlg();
        if(not result):      
                time.sleep(pause)
                continue;
        #baseline = ccs811.readBaseline()
        print 'eCO2: ',result['eCO2'],'ppm'
	print "temperature: %0.1f" % temperature
	print "humidity: %0.1f" % humidity
        print '---------------------------------';

        time.sleep(pause)