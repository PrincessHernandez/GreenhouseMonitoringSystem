package com.greenhouseproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HistoryFragment extends Fragment{
    View myView;
    //EditText xVal, yVal;
    //Button btnInsert;
    String gas_n[] = {"temp", "co2", "humid"};

    FirebaseDatabase database;
    DatabaseReference ref;
    GraphView graphView;
    LineGraphSeries series[] = {new LineGraphSeries(), new LineGraphSeries(), new LineGraphSeries()};

    FirebaseUser authData = FirebaseAuth.getInstance().getCurrentUser() ;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM d\nHH:mm");
    // HH:mm:ss or

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_history, container, false);

        graphView = (GraphView) myView.findViewById(R.id.graph);

        for (int i=0; i < 3; i++) {
            graphView.addSeries(series[i]);
            series[i].setTitle(gas_n[i]);
        }
        series[1].setColor(Color.GREEN);
        series[2].setColor(Color.RED);
        //series[3].setColor(Color.MAGENTA);

        // legend
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference(authData.getUid());

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return  sdf.format(new Date((long) value));
                }
                return super.formatLabel(value, isValueX);
            }
        });

        //setListeners();

        return myView;
    }
/*
    private void setListeners() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ref.push().getKey();

                int x = Integer.parseInt(xVal.getText().toString());
                int y = Integer.parseInt(yVal.getText().toString());

                PointValue pointValue = new PointValue(x,y);

                ref.child(id).setValue(pointValue);
            }
        });
    }
*/

    @Override
    public void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataPoint[][] dp = new DataPoint[3][(int) dataSnapshot.getChildrenCount()];

                int index = 0;
                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    DataValue dataValue = myDataSnapshot.getValue(DataValue.class);
                    int ValArr[] = {dataValue.getTempValue(),dataValue.getCo2Value(),dataValue.getHumidityValue()};

                    for (int i=0; i < 3; i++) {
                        dp[i][index] = new DataPoint(dataValue.getDate(),ValArr[i]);
                    }
                    index++;
                }

                for (int i=0; i < 3; i++) {
                    series[i].resetData(dp[i]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

