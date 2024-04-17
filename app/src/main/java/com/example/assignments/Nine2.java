package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class Nine2 extends AppCompatActivity {
    EditText editTextSearch;
    Button btnSearch;
    TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine2);



        editTextSearch = findViewById(R.id.editTextSearch);
        btnSearch = findViewById(R.id.btnSearch);
        textViewResult = findViewById(R.id.textViewResult);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = editTextSearch.getText().toString();

                // Read student information from the file and search for the student
                String result = searchStudent(searchName);
                textViewResult.setText(result);
            }
        });
    }

    private String searchStudent(String searchName) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
           FileInputStream fileInputStream = openFileInput("student_data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchName)) {
                    stringBuilder.append(line).append("\n");
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stringBuilder.length() == 0) {
            return "Student not found!";
        } else {
            return stringBuilder.toString();
        }
    }
}
