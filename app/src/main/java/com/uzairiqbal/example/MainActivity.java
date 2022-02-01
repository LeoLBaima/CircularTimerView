package com.uzairiqbal.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uzairiqbal.circulartimerview.CircularTimerListener;
import com.uzairiqbal.circulartimerview.CircularTimerView;
import com.uzairiqbal.circulartimerview.TimeFormatEnum;

public class MainActivity extends AppCompatActivity {

    Button btnReset;
    CircularTimerView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReset = findViewById(R.id.btnRestart);
        progressBar = findViewById(R.id.progress_circular);

        progressBar.setCircularTimerListener(new CircularTimerListener() {
            @Override
            public String updateDataOnTick(long remainingTimeInMs) {
                int minutes = (int) (remainingTimeInMs / 1000 / 60);
                int seconds = (int) remainingTimeInMs / 1000 % 60;
                if (String.valueOf(seconds).length() < 2) {
                    return String.valueOf(minutes) + ":0" + String.valueOf(seconds);
                } else {
                    return String.valueOf(minutes)+ ":" + String.valueOf(seconds);
                }
            }

            @Override
            public void onTimerFinished() {
                Toast.makeText(MainActivity.this, "FINISHED", Toast.LENGTH_SHORT).show();
                progressBar.setPrefix("");
                progressBar.setSuffix("");
                progressBar.setText("FINISHED THANKS!");
            }
        }, 15, TimeFormatEnum.MINUTES, 10);

        progressBar.startTimer();
        progressBar.setProgress(50L);


        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setProgress(0L);
                progressBar.startTimer();
            }
        });
    }
}
