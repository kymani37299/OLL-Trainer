package com.example.marko.olltrainer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marko.olltrainer.model.OLLCase;
import com.example.marko.olltrainer.wigets.SimpleChronometer;

import java.util.ArrayList;
import java.util.Random;

public class TrainActivity extends AppCompatActivity {

    // TODO : Timer stop on touch , start on click

    private static final Random rand =  new Random();

    private SimpleChronometer cTimer;
    private boolean timerRunning = false;
    private TextView tvScramble;

    private ArrayList<OLLCase> selectedCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        cTimer = findViewById(R.id.timer);
        tvScramble = findViewById(R.id.scramble_text);

        selectedCases = MainActivity.getSelectedCases();

        OLLCase randomCase = selectedCases.get(rand.nextInt(selectedCases.size()));

        tvScramble.setText(randomCase.getScramble());

    }

    public void timer_action(View view) {
        if(timerRunning) {
            cTimer.stop();
            OLLCase randomCase = selectedCases.get(rand.nextInt(selectedCases.size()));
            tvScramble.setText(randomCase.getScramble());
        } else {
            cTimer.setBase(SystemClock.elapsedRealtime());
            cTimer.start();
        }
        timerRunning = !timerRunning;
    }

}
