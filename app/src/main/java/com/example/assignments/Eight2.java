package com.example.assignments;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class Eight2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight2);

                    TextView textViewPlayerName, textViewPoints;
                    textViewPlayerName = findViewById(R.id.textViewPlayerName);
                textViewPoints = findViewById(R.id.textViewPoints);

                String playerName = getIntent().getStringExtra("PLAYER_NAME");
                String points = getIntent().getStringExtra("POINTS");

                textViewPlayerName.setText("Player Name: " + playerName);
                textViewPoints.setText("Points: " + points);
            }
        }

