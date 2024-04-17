package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class One extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        EditText usr=(EditText) findViewById(R.id.username);
        EditText pass=(EditText) findViewById(R.id.password);
        Button button=(Button) findViewById(R.id.button);
        TextView forgot=(TextView)findViewById(R.id.forgot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=usr.getText().toString();
                String password=pass.getText().toString();
                if(username.equals("om") && password.equals("123456")){
                    Intent intent= new Intent (getApplicationContext(), One_2.class);
                    startActivity(intent);
                }
                else {
                    // to get Context
                    Context context = getApplicationContext();
// message to display
                    String text = "Invalid Login !";
// toast time duration, can also set manual value
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
// to show the toast
                    toast.show();

                }
            }
        });



}}