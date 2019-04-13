package com.greenhouseproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class HumidityFragment extends Fragment{
    View myView;

    String gas_n = "humid";

    //String[] mobileArray = {"15","18", "14"};

    //ArrayAdapter adapter;
    private ListView listView;

    FirebaseDatabase database;
    DatabaseReference ref;
    String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    GraphView graphView;
    LineGraphSeries series = new LineGraphSeries();

    FirebaseUser authData = FirebaseAuth.getInstance().getCurrentUser() ;
    SimpleDateFormat sdf = new SimpleDateFormat("MMM d\nHH:mm");
    // HH:mm:ss or

    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_humidity_fragment, container, false);

        graphView = (GraphView) myView.findViewById(R.id.graph);

        graphView.addSeries(series);
        series.setTitle(gas_n);


        adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.activity_listview, list);
        adapter.notifyDataSetChanged();
        listView = (ListView) myView.findViewById(R.id.listViewHumid);



        // legend
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Data");

        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return  sdf.format(new Date((long) value * 1000));
                }
                return super.formatLabel(value, isValueX);
            }
        });


        //setListeners();

        return myView;
    }

    @Override
    public void onStart() {
        super.onStart();
        ref.child("pi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index = 0;

                listView.setAdapter(adapter);
                list.clear();

                for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                    float humid;
                    String seconds;
                    try {
                        humid = myDataSnapshot.child("humidity").getValue(float.class);
                        seconds = myDataSnapshot.child("seconds").getValue(String.class);

                    } catch (NullPointerException e) {
                        humid = 0;
                        seconds = "1550000000";
                    }

                    dp[index] = new DataPoint(Double.parseDouble(seconds) ,humid);
                    list.add(String.valueOf(humid));

                    index++;
                }


                series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

