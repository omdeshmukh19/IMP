package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Twelve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve);
                Button button;
                EditText txt;
                button=findViewById(R.id.check);
                txt=findViewById(R.id.txt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(txt.length()==0){
                            txt.setError("required!");
                        }
                        else {
                            TextView dis = findViewById(R.id.dis);
                            String pal = "";
                            String res = txt.getText().toString();
                            for (int i = res.length() - 1; i >= 0; i--) {
                                pal = pal + res.charAt(i);
                            }
                            if (res.equals(pal)) {
                                dis.setText("Palindrome");
                            } else {
                                dis.setText("Not Palindrome");
                            }
                        }
                    }
                });
            }
        }