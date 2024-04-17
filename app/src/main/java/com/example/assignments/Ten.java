package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Ten extends AppCompatActivity {
    private EditText editTextNumber1, editTextNumber2;
    private Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(editTextNumber1.getText().toString());
                int number2 = Integer.parseInt(editTextNumber2.getText().toString());

                if (number1 > 10 && number2 > 10) {
                    // Reject input if both numbers are greater than 10
                    Toast.makeText(Ten.this, "Both numbers are greater than 10. Please enter two new numbers.", Toast.LENGTH_SHORT).show();
                    editTextNumber1.setText("");
                    editTextNumber2.setText("");
                    editTextNumber1.requestFocus();
                } else {
                    // Display the numbers
                    Toast.makeText(Ten.this, "Number 1: " + number1 + ", Number 2: " + number2, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}