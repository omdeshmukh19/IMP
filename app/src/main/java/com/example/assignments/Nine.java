package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Nine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);

            EditText editTextName,no; // Add other EditText fields for student information
            Button btnSubmit;
            editTextName =findViewById(R.id.editTextName);
            no= findViewById(R.id.rollNo);
            btnSubmit =findViewById(R.id.btnSubmit);

                btnSubmit.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                String studentName = editTextName.getText().toString();
                String rollNo=no.getText().toString();

                // Write student information to a file
                writeToFile(studentName,rollNo);

                // Navigate to the display activity
                Intent intent = new Intent(Nine.this, Nine2.class);
                startActivity(intent);
            }
            });
        }

        private void writeToFile (String name, String rollNo){
            try {
                FileOutputStream fileOutputStream = openFileOutput("student_data.txt", MODE_APPEND);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(name + "\n"+ rollNo);
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


