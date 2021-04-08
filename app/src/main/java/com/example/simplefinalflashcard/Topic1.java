package com.example.simplefinalflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Topic1 extends AppCompatActivity
        implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private Button nextButton;
    private Button prevButton;
    private TextView questionTextView;
    private int correct = 0;
    Button buttonhome;

    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {

            new Question(R.string.b, true),
            new Question(R.string.c, true),
            new Question(R.string.d, false),
            new Question(R.string.e, true),


    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic1);

        buttonhome = (Button) findViewById(R.id.home_button);
        buttonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Topic1.this, MainActivity.class);
                startActivity(intent);
            }
        });
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        questionTextView
                = findViewById(R.id.answer_text_view);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {

        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:

                if (currentQuestionIndex < 5) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;

                    if (currentQuestionIndex == 4) {
                        questionTextView.setText(getString(
                                R.string.correct, correct));
                        nextButton.setVisibility(
                                View.INVISIBLE);
                        prevButton.setVisibility(
                                View.INVISIBLE);
                        trueButton.setVisibility(
                                View.INVISIBLE);
                        falseButton.setVisibility(
                                View.INVISIBLE);
                        if (correct > 2)

                            questionTextView.setText(
                                    "SCORE: " + correct
                                            + " "
                                            + "OUT OF 4");

                        else;
                    }
                    else {
                        updateQuestion();
                    }
                }

                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = (currentQuestionIndex - 1)
                            % questionBank.length;
                    updateQuestion();
                }
        }
    }

    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());

    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();

        int toastMessageId;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {

            toastMessageId = R.string.wrong_answer;
        }

        Toast
                .makeText(Topic1.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}