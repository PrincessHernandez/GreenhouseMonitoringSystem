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
import android.widget.Toast;

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

    EditText tempVal, co2Val, humidVal;
    Button btnCData;
    FirebaseDatabase database;
    DatabaseReference ref;

    FirebaseUser authData = FirebaseAuth.getInstance().getCurrentUser() ;

    PieChartView pieChartView[] = new PieChartView[3];
    List<SliceValue> pieData[] = new List[3];
    PieChartData pieChartData[] = new PieChartData[3];
    String gas_n[] = {"TEMP", "CO2", "Humid"};

    private static final String TAG = "tag me";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_summary, container, false);

        tempVal = (EditText) myView.findViewById(R.id.tempVal);
        //vocVal = (EditText) myView.findViewById(R.id.vocVal);
        co2Val = (EditText) myView.findViewById(R.id.co2Val);
        humidVal = (EditText) myView.findViewById(R.id.humidVal);
        btnCData = (Button) myView.findViewById(R.id.btn_current_data);

        database = FirebaseDatabase.getInstance();
        //ref = database.getReference(authData.getUid());
        ref = database.getReference("Current Data");

        pieChartView[0] = myView.findViewById(R.id.chart_temp);
        //pieChartView[1] = myView.findViewById(R.id.chart_voc);
        pieChartView[1] = myView.findViewById(R.id.chart_co2);
        pieChartView[2] = myView.findViewById(R.id.chart_humid);

        setListeners();

        return myView;
    }

    private void setListeners() {
        btnCData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ref.push().getKey();

                int temp = Integer.parseInt(tempVal.getText().toString());
                //int voc = Integer.parseInt(vocVal.getText().toString());
                int co2 = Integer.parseInt(co2Val.getText().toString());
                int humid = Integer.parseInt(humidVal.getText().toString());

                DataValue dataValue = new DataValue(temp, co2, humid);
                ref.child(id).setValue(dataValue);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    DataValue dataValue = myDataSnapshot.getValue(DataValue.class);
                    int ValArr[] = {dataValue.getTempValue(),dataValue.getCo2Value(),dataValue.getHumidityValue()};

                    for (int i=0; i < 3; i++){
                        pieData[i] = new ArrayList<>();
                        pieData[i].add(new SliceValue(ValArr[i], Color.GREEN));
                        pieData[i].add(new SliceValue(100-ValArr[i], Color.LTGRAY));

                        pieChartData[i] = new PieChartData(pieData[i]);
                        //pieChartData[i].setHasLabels(true).setValueLabelTextSize(14);
                        pieChartData[i].setHasCenterCircle(true).setCenterText1(gas_n[i]+": " + Integer.toString(ValArr[i])+"%").setCenterText1FontSize(25);
                        pieChartView[i].setPieChartData(pieChartData[i]);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}


/*
public class SummaryFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_summary, container, false);
        return myView;
    }
}

*/

