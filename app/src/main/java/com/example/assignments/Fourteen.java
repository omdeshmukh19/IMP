package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class Fourteen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourteen);


                RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup);
                Button click = (Button) findViewById(R.id.click);
                TextView res = (TextView) findViewById(R.id.res);
                EditText number = (EditText) findViewById(R.id.number);
                click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton radio = (RadioButton)findViewById(grp.getCheckedRadioButtonId());
                        if(radio!=null && !number.getText().toString().isEmpty()) {
                            String str = radio.getText().toString();
                            int num = Integer.parseInt(number.getText().toString());
                            switch (str) {
                                case "Odd or Even":
                                    if(num%2==0){
                                        res.setText("Even");
                                    }else{
                                        res.setText("Odd");
                                    }
                                    break;
                                case "Positive or Negative":
                                    if(num<0){
                                        res.setText("Negative");
                                    }else{
                                        res.setText("Positive");
                                    }
                                    break;
                                case "Square":
                                    res.setText(String.valueOf(num*num));
                                    break;
                                case "Factorial":
                                    if(num==0){
                                        res.setText("1");
                                    }else {
                                        double fac = 1;
                                        for (int i = 1; i <= num; i++) {
                                            fac = fac * i;
                                        }
                                        res.setText(String.valueOf(fac));
                                    }
                                    break;
                            }
                        }
                    }
                });

            }
        }
