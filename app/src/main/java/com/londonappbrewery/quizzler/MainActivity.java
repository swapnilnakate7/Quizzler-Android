package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare constants here


    // TODO: Declare member variables here:
    Button mtrueButton;
    Button mfalseButton;
    TextView questionTxtView;
    Integer qIndex;
    Integer score;
    TextView scoreText;
    ProgressBar progress;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };
    final int PROGRESS_INCR = (int) Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qIndex = 0;
        score = 0;
        mtrueButton = (Button) findViewById(R.id.true_button);
        mfalseButton = (Button) findViewById(R.id.false_button);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        questionTxtView = (TextView) findViewById(R.id.question_text_view);
        questionTxtView.setText(mQuestionBank[qIndex].getQuestionID());
        scoreText = (TextView) findViewById(R.id.score);


        //True Button
        mtrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"True Pressed",Toast.LENGTH_SHORT).show();
                checkAnswer(true);

                updateQuestion();


            }
        });
        //False Button
        mfalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"False Pressed",Toast.LENGTH_SHORT).show();
                checkAnswer(false);


                updateQuestion();


            }
        });

    }

    public void updateQuestion() {

        qIndex = (qIndex + 1) % mQuestionBank.length;
        if (qIndex == 0) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Your Score is " + score);
            alert.setPositiveButton("Close Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
            score = 0;
        }
        questionTxtView.setText(mQuestionBank[qIndex].getQuestionID());
        scoreText.setText(score + "/" + mQuestionBank.length);
        progress.incrementProgressBy(PROGRESS_INCR);

    }

    public void checkAnswer(Boolean b) {
        Boolean answer = mQuestionBank[qIndex].getAnswer();
        if (answer == b) {
            score++;
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }

    }


}
