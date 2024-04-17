package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class Fifteen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifteen);



                EditText str = (EditText) findViewById(R.id.str);
                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup);
                Button click = (Button) findViewById(R.id.click);
                TextView res = (TextView) findViewById(R.id.res);
                click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton radio = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                        if(radio!=null && !str.getText().toString().isEmpty()){
                            String option = radio.getText().toString();
                            String operation = str.getText().toString();
                            switch (option){
                                case "Uppercase":
                                    res.setText(operation.toUpperCase());
                                    break;
                                case "Lowercase":
                                    res.setText(operation.toLowerCase());
                                    break;
                                case "First 5 Characters":
                                    if(operation.length()<5){
                                        res.setText("not enough characters!");
                                    }else{
                                        String result=operation.substring(0,5);
                                        res.setText(result);
                                    }
                                    break;
                                case "Last 5 Characters":
                                    if(operation.length()-4>0){
                                        String result=operation.substring(operation.length()-5,operation.length());
                                        res.setText(result);
                                    }else{
                                        res.setText("not enough characters!");
                                    }
                            }
                        }
                    }
                });
            }
        }
