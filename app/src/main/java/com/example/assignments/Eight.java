package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Eight extends AppCompatActivity {

    EditText editTextPlayerName, editTextPoints;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        editTextPlayerName = findViewById(R.id.editTextPlayerName);
        editTextPoints = findViewById(R.id.editTextPoints);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = editTextPlayerName.getText().toString();
                String points = editTextPoints.getText().toString();

                Intent intent = new Intent(Eight.this, Eight2.class);
                intent.putExtra("PLAYER_NAME", playerName);
                intent.putExtra("POINTS", points);
                startActivity(intent);
            }
        });
    }
}
