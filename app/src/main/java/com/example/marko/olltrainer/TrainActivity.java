package com.example.marko.olltrainer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marko.olltrainer.model.OLLCase;
import com.example.marko.olltrainer.wigets.SimpleChronometer;

import java.util.ArrayList;
import java.util.Random;

public class TrainActivity extends AppCompatActivity {

    private static final Random rand =  new Random();

    private SimpleChronometer cTimer;
    private boolean timerRunning = false;
    private TextView tvScramble;
    private LinearLayout mainLayout;

    private ArrayList<OLLCase> selectedCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        cTimer = findViewById(R.id.timer);
        tvScramble = findViewById(R.id.scramble_text);
        mainLayout = findViewById(R.id.train_layout);

        selectedCases = MainActivity.getSelectedCases();

        OLLCase randomCase = selectedCases.get(rand.nextInt(selectedCases.size()));

        tvScramble.setText(randomCase.getScramble());

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(timerRunning) {
                    cTimer.stop();
                    OLLCase randomCase = selectedCases.get(rand.nextInt(selectedCases.size()));
                    tvScramble.setText(randomCase.getScramble());
                }
                return false;
            }
        });

    }

    public void timer_action(View view) {
        if(!timerRunning) {
            cTimer.setBase(SystemClock.elapsedRealtime());
            cTimer.start();
        }
        timerRunning = !timerRunning;

    }

}
