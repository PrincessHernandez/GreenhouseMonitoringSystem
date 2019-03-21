import datetime
import time
from tentacle_pi.AM2315 import AM2315
from CCS811_RPi import CCS811_RPi

import Adafruit_GPIO.SPI as SPI
import Adafruit_SSD1306

from PIL import Image
from PIL import ImageDraw
from PIL import ImageFont

import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
cred = credentials.Certificate('/home/pi/GreenhouseMonitoringSystem/greenhouseproject.json')
default_app = firebase_admin.initialize_app(cred, {
    'databaseURL':'https://greenhouseproject-58231.firebaseio.com'
})
ref = db.reference('Data')


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

# Raspberry Pi pin configuration:
RST = 24
# Note the following are only used with SPI:
DC = 23
SPI_PORT = 0
SPI_DEVICE = 0

# 128x32 display with hardware I2C:
disp = Adafruit_SSD1306.SSD1306_128_32(rst=RST, i2c_address=0x3C)

# Initialize library.
disp.begin()

# Clear display.
disp.clear()
disp.display()

# Create blank image for drawing.
# Make sure to create image with mode '1' for 1-bit color.
width = disp.width
height = disp.height
image = Image.new('1', (width, height))

# Get drawing object to draw on image.
draw = ImageDraw.Draw(image)

# Draw a black filled box to clear the image.
draw.rectangle((0,0,width,height), outline=0, fill=0)

# Draw some shapes.
# First define some constants to allow easy resizing of shapes.
padding = -2
top = padding
bottom = height-padding
# Move left to right keeping track of the current x position for drawing shapes.
x = 0

# Load default font.
font = ImageFont.load_default()
user_ref = ref.child('users')
user_ref.set({
    'pi':{
        'full_name' : 'Raspberry Pi',
        'Temperature' : '0',
        'Humidity' : '0',
        'eC02' : '0'
        }
})
while True:
    curtime = datetime.datetime.now()
    temperature, humidity, crc_check = am.sense()
    result = ccs811.readAlg();
    posts_ref = ref.child('pi')
    new_post_ref = posts_ref.push()
    new_post_ref.set({
        'Temperature' : temperature,
        'Humidity' : humidity,
        'eC02' : result,
        'time' : str(curtime),
})
    #str_co2 = 'eCO2: ', result['eCO2'], ' ppm'
    #str_temp = 'temperature: %0.1f' % temperature
    #str_hum = 'humidity: %0.1f' % humidity

    draw.rectangle((0,0,width,height), outline=0, fill=0)
    if(result['eCO2'] > 8192):
       draw.text((x, top),       "eCO2: "+ "Invalid",  font=font, fill=255)
    else:
       # Write two lines of text.
        draw.text((x, top),       "eCO2: "+ str(result['eCO2']) + " ppm",  font=font, fill=255)

    draw.text((x, top+8),     "temperature: %0.1f" % temperature, font=font, fill=255)
    draw.text((x, top+16),    "humidity: %0.1f" % humidity,  font=font, fill=255)

    # Display image.
    disp.image(image)
    disp.display()
    
    time.sleep(pause)
