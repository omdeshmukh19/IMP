package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Three extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

            List<String> categories = new ArrayList<String>();
                EditText txt = (EditText) findViewById(R.id.txt);
                Button btn1 = (Button) findViewById(R.id.add);
                Spinner spinner = (Spinner) findViewById(R.id.spinner2);
                Button btn2 = (Button) findViewById(R.id.rmv);
                TextView res = (TextView) findViewById(R.id.res);
                categories.add("Om");
                categories.add("Pratik");
                categories.add("Sakshi");
                ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter1);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!txt.getText().toString().isEmpty()) {
                            String str = txt.getText().toString();
                            categories.add(str);
                            res.setText(String.format("'%s' added to Spinner!", str));
                        }
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!txt.getText().toString().isEmpty()) {
                            String str = txt.getText().toString();
                            if (categories.contains(str)) {
                                categories.remove(str);
                                res.setText(String.format("'%s' removed from Spinner!", str));
                            } else {
                                res.setText(String.format("Spinner doesn't contain '%s'", str));
                            }
                        }
                    }
                });
            }
        }
