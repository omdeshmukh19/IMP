package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityButtonController;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


                EditText phone, message;
                Button send;
                phone = findViewById(R.id.phone);
                message = findViewById(R.id.message);
                send = findViewById(R.id.send);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String number = phone.getText().toString();
                        String msg = message.getText().toString();
                        try {
                            Intent intent = new Intent(getApplicationContext(), Sms.class);
                            PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(number, null, msg, null, null);
                            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_LONG).show();
                            message.setText("");
                        } catch (Exception e) {
                            Log.d("Error", e.getMessage());
                        }
                    }
                });

            }
        }
