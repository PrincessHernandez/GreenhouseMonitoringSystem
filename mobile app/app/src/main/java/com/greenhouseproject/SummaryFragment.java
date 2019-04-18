package com.greenhouseproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SummaryFragment extends Fragment{

    View myView;

    TextView tempText, humidText, co2Text, statText;
    Button btnCData;
    FirebaseDatabase database;
    DatabaseReference ref;

    FirebaseUser authData = FirebaseAuth.getInstance().getCurrentUser() ;

    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    PieChartView pieChartView[] = new PieChartView[3];
    List<SliceValue> pieData[] = new List[3];
    PieChartData pieChartData[] = new PieChartData[3];
    String gas_n[] = {"TEMP", "CO2", "Humid"};

    private static final String TAG = "tag me";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_summary, container, false);

        /*
        tempVal = (EditText) myView.findViewById(R.id.tempVal);
        //vocVal = (EditText) myView.findViewById(R.id.vocVal);
        co2Val = (EditText) myView.findViewById(R.id.co2Val);
        humidVal = (EditText) myView.findViewById(R.id.humidVal);
        btnCData = (Button) myView.findViewById(R.id.btn_current_data);
*/
        statText = (TextView) myView.findViewById(R.id.textViewStat);
        tempText = (TextView) myView.findViewById(R.id.textViewTemp);
        humidText = (TextView) myView.findViewById(R.id.textViewHumid);
        co2Text = (TextView) myView.findViewById(R.id.textViewCo2);

        database = FirebaseDatabase.getInstance();
        //ref = database.getReference(authData.getUid());
        //ref = database.getReference("Current Data");
        ref = database.getReference("Data"); // --  --  -- Ref here --  --  --

        //pieChartView[0] = myView.findViewById(R.id.chart_temp);
        //pieChartView[1] = myView.findViewById(R.id.chart_co2);
        //pieChartView[2] = myView.findViewById(R.id.chart_humid);

      //  setListeners();

        return myView;
    }
/*
    private void setListeners() {
        btnCData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ref.push().getKey();

                int temp = Integer.parseInt(tempVal.getText().toString());
                int co2 = Integer.parseInt(co2Val.getText().toString());
                int humid = Integer.parseInt(humidVal.getText().toString());

                //DataValue dataValue = new DataValue(temp, co2, humid);
               // ref.child(userid).child(id).setValue(dataValue);
            }
        });
    }
*/

    @Override
    public void onStart() {
        super.onStart();
        //userid
        ref.child("pi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    float temp, humid;
                    int eco2;
                    try {
                        temp = myDataSnapshot.child("temperature").getValue(Float.class);
                        humid = myDataSnapshot.child("humidity").getValue(Float.class);
                        eco2 = myDataSnapshot.child("eC02/eCO2").getValue(int.class);

                    } catch (NullPointerException e) {
                        temp = 0;
                        humid = 0;
                        eco2 = 0;
                    } catch (IllegalArgumentException e) {
                        temp = 1;
                        humid = 1;
                        eco2 = 1;
                    }

                    //Data dataValue = myDataSnapshot.getValue(Data.class);
                    //Data myCO2Value = myDataSnapshot.child("eC02").getValue(Data.class);

                    tempText.setText("Temp: " + temp + "°C");
                    humidText.setText("Humidity: " + humid + "%");
                    co2Text.setText("Co2: " + eco2 + " ppm");

                    if (eco2 > 8192){
                        co2Text.setText("Co2: Invalid");
                    }

                    if (temp == 0 && humid == 0 && eco2 == 0){
                        statText.setText("Database Error");
                        statText.setTextColor(Color.GRAY);
                    }
                    else if (temp <= 20 ) {
                        statText.setText("Turn on heater");
                        statText.setTextColor(Color.RED);
                    }
                    else if (temp >= 30 ) {
                        statText.setText("Turn on fans or open windows");
                        statText.setTextColor(Color.RED);
                    }
                    else {
                        if (humid <= 80) {
                            statText.setText("Watering required!!!");
                            statText.setTextColor(Color.RED);
                        } else {
                            statText.setText("Normal");
                            statText.setTextColor(Color.GREEN);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}


