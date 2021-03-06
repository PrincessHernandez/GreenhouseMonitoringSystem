# This code should be used with every script that you will be using to connect to the Firebase database.
import pyrebase
from firebase import firebase
config = {
  # You can get all these info from the firebase website. It's associated with your account.
  "apiKey": "AIzaSyA3SmPEZdgyxWIcENXi1V098gof4MvAdSI",
  "authDomain": "greenhouseproject-58231.firebaseapp.com",
  "databaseURL": "https://greenhouseproject-58231.firebaseio.com",
  "storageBucket": "greenhouseproject-58231.appspot.com"
}

firebase = pyrebase.initialize_app(config)
db = firebase.database()
while 1:
        humidity, temperature = Adafruit_DHT.read_retry(Adafruit_DHT.DHT22, 4)
        time_hhmmss = time.strftime('%H:%M:%S')
        date_mmddyyyy = time.strftime('%d/%m/%Y')
        data = {"Date": date_mmddyyyy,"Time": time_hhmmss, "Temperature": temperature, "Humidity": humidity}
        db.child("/message").push(data)
        print("Temp: %f -- Date: %s  -- Time: %s" %(x,date_mmddyyyy,time_hhmmss))
        time.sleep(60)
