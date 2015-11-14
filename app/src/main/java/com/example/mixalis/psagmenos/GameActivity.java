package com.example.mixalis.psagmenos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by mixalis on 14/11/2015.
 */
public class GameActivity extends Activity {
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private ProgressBar progressBar;
    ProgressBar bar;
    String epelexes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
        // ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        Intent intent = getIntent();
        String fName = intent.getStringExtra("firstName");
        String lName = intent.getStringExtra("lastName");
        epelexes=fName;


        TextView title = (TextView) findViewById(R.id.title);
title.setText(epelexes);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the

                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                           // textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.

                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    }



