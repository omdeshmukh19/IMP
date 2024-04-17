package com.example.assignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Four extends AppCompatActivity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;
    private TextView resultTextView;

    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

                questionTextView = findViewById(R.id.questionTextView);
                optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
                submitButton = findViewById(R.id.submitButton);
                resultTextView = findViewById(R.id.resultTextView);

                questions = new ArrayList<>();
                questions.add(new Question("Which folder do you copy and paste an image into", new String[]{"Layout", "Resources", "Drawable", "Java"}, 2));
                questions.add(new Question("Which component property should be changed to a name that is specific of the components use?", new String[]{"Text", "ID", "Editable", "Content Description"}, 1));

                showQuestion();

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check user's answer
                        int selectedOptionIndex = optionsRadioGroup.getCheckedRadioButtonId();
                        if (selectedOptionIndex != -1) {
                            RadioButton selectedRadioButton = findViewById(selectedOptionIndex);
                            int answerIndex = optionsRadioGroup.indexOfChild(selectedRadioButton);

                            // Check if the answer is correct
                            if (answerIndex == questions.get(currentQuestionIndex).getCorrectOptionIndex()) {
                                score++;
                            }

                            // Move to the next question or show result
                            if (currentQuestionIndex < questions.size() - 1) {
                                currentQuestionIndex++;
                                showQuestion();
                            } else {
                                showResult();
                            }
                        } else {
                            Toast.makeText(Four.this, "Please select an option", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            private void showQuestion() {
                Question currentQuestion = questions.get(currentQuestionIndex);
                questionTextView.setText(currentQuestion.getQuestion());
                optionsRadioGroup.removeAllViews();
                for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(currentQuestion.getOptions()[i]);
                    optionsRadioGroup.addView(radioButton);
                }
            }

            private void showResult() {
                resultTextView.setText("Score: " + score + "/" + questions.size());
                submitButton.setVisibility(View.GONE);
                resultTextView.setVisibility(View.VISIBLE);
            }




    public class Question {
        private String question;
        private String[] options;
        private int correctOptionIndex;

        public Question(String question, String[] options, int correctOptionIndex) {
            this.question = question;
            this.options = options;
            this.correctOptionIndex = correctOptionIndex;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectOptionIndex() {
            return correctOptionIndex;
        }
    }

}
