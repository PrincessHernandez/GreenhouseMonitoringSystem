package com.greenhouseproject;

import java.util.Date;

public class Data {
    float temperature, humidity;
    int eCO2;
    String seconds;

    public Data() {
    }

    public Data(float temperature, float humidity, int eCO2, String seconds) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.eCO2 = eCO2;
        this.seconds = seconds;
    }

    public float getTemperature() { return temperature; }

    public float getHumidity() { return humidity; }

    public int geteCO2() { return eCO2; }

    public String getDate() { return  seconds; }
}
