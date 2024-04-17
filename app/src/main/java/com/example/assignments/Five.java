package com.example.assignments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Five extends AppCompatActivity {




        //Set Date Function
            public void  setDate(TextView viewbox, DatePicker picker){
                String day = String.valueOf(picker.getDayOfMonth());
                String month = String.valueOf(picker.getMonth()+1);
                String year = String.valueOf(picker.getYear());
                viewbox.setText(day+"/"+month+"/"+year);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_five);
                DatePicker picker = (DatePicker) findViewById(R.id.dates);
                Button select = (Button) findViewById(R.id.select);
                TextView viewbox = (TextView) findViewById(R.id.viewbox);

                //Default Date
                setDate(viewbox,picker);

                //Date Change Listener

                picker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        setDate(viewbox,picker);
                    }
                });

                //Put Extra Listener
                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth()+1);
                        String year = String.valueOf(picker.getYear());
                        Intent i = new Intent(Five.this, Five2.class);
                        i.putExtra("day",day);
                        i.putExtra("month",month);
                        i.putExtra("year",year);
                        startActivity(i);
                    }
                });


            }
        }
