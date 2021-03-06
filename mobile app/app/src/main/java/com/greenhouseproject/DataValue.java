package com.greenhouseproject;

import java.util.Date;

public class DataValue {
    int tempValue, co2Value, humidityValue;
    float Temperature, Humidity;
    Date date = new Date();
    //Long timestamp;

    public DataValue() {
    }

    public DataValue(int tempValue,int co2Value,int humidityValue) {
        this.tempValue = tempValue;
        //this.vocValue = vocValue;
        this.co2Value = co2Value;
        this.humidityValue = humidityValue;

        //this.temperature = temperature;
        //this.timestamp = System.currentTimeMillis();
        this.date.getTime();
    }

    public int getTempValue() {
        return tempValue;
    }

    //public int getVocValue() {
    //    return vocValue;
    //}

    public int getCo2Value() {
        return co2Value;
    }

    public int getHumidityValue() {
        return humidityValue;
    }

    public float getTemperature() { return Temperature; }

    public float getHumidity() { return Humidity; }

    public Date getDate() { return  date; }
}
