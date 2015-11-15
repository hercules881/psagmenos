package com.example.mixalis.psagmenos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import Database.Answer;
import Database.ExternalDbOpenHelper;
import Database.Question;

/**
 * Created by mixalis on 14/11/2015.
 */
public class GameActivity extends Activity {
    private int progressStatus = 101;
    private Handler handler = new Handler();
    private ProgressBar progressBar;
    ProgressBar bar;
    String epelexes;
    private Button apantisi1;
    private Button apantisi2;
    private Button apantisi3;
    private Button apantisi4;
    private int lifes = 3;
    ArrayList<Integer> lastQuestionNumber = new ArrayList<>();
    int randomNumer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
        final TextView erwtisi = (TextView) findViewById(R.id.erwtisi);

        apantisi1 = (Button) findViewById(R.id.apantisi1);
        apantisi2 = (Button) findViewById(R.id.apantisi2);
        apantisi3 = (Button) findViewById(R.id.apantisi3);
        apantisi4 = (Button) findViewById(R.id.apantisi4);

        // ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        Intent intent = getIntent();
        String fName = intent.getStringExtra("firstName");
        String lName = intent.getStringExtra("lastName");
        epelexes=fName;



        final ExternalDbOpenHelper dbHelper = new ExternalDbOpenHelper(this);
        final ArrayList<Question> questions= (ArrayList<Question>) dbHelper.getQuestionForCategory(epelexes);   //erwtiseis
        //pairnoume tuxaio arithmo gia erwtisi!
        randomNumer = getRandomNumer(questions.size());
        //kratame ton arithmo tis proigoumenis erwtisis gia na min ksanapesei!
        lastQuestionNumber.add(randomNumer);
        questions.get(randomNumer-1);
        erwtisi.setText(questions.get(randomNumer-1).getText());


      ArrayList<Answer>answers=(ArrayList<Answer>) dbHelper.getPossibleAnswersForQuestion(questions.get(randomNumer-1));//apantiseis
        answers.get(0);
        apantisi1.setText(answers.get(0).getText());
        apantisi2.setText(answers.get(1).getText());
        apantisi3.setText(answers.get(2).getText());
        apantisi4.setText(answers.get(3).getText());

        // answers.get(0).getIsValidAnswer(); an einai swsti i pantisi einai 1 aliws einai 0!


        TextView title = (TextView) findViewById(R.id.title);
title.setText(epelexes);
        new Thread(new Runnable() {
            public void run() {

                while (progressStatus > 0) {
                    progressStatus -= 1;
                    // Update the progress bar and display the
                    //current value in the text view

                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //an teleivse o xronos allakse erwtisi!!!
                            if(progressStatus == 0){
                                randomNumer = getRandomWithExclusion(new Random(), 1 , questions.size() , lastQuestionNumber);

                                //kratame ton arithmo tis proigoumenis erwtisis gia na min ksanapesei!
                                lastQuestionNumber.add(randomNumer-1);
                                erwtisi.setText(questions.get(randomNumer-1).getText());
                                ArrayList<Answer>answers=(ArrayList<Answer>) dbHelper.getPossibleAnswersForQuestion(questions.get(randomNumer-1));//apantiseis
                                answers.get(0);
                                apantisi1.setText(answers.get(0).getText());
                                apantisi2.setText(answers.get(1).getText());
                                apantisi3.setText(answers.get(2).getText());
                                apantisi4.setText(answers.get(3).getText());
                                progressStatus = 101;
                                findViewById(lifes ==3?R.id.zwi3:lifes == 2? R.id.zwi2 : R.id.zwi1).setVisibility(View.INVISIBLE);
                                lifes--;
                                if(lifes == 0){
                                    AlertDialog.Builder b = new AlertDialog.Builder(GameActivity.this);
                                    b.setTitle("ΧΑΣΑΤΕ!");
                                    b.setMessage("Λυπάμαι, τον ήπιες!");
                                    b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            GameActivity.this.finish();
                                        }
                                    });
                                    b.create().show();

                                }
                            }
                           // textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    //an teleiwsoun oi zwes stamata tin loopa!!!
                    if (lifes == 0)
                        break;

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


        private int getRandomNumer(int size){
            Random r = new Random();
          return   randomNumer = r.nextInt(size);
        }

    public int getRandomWithExclusion(Random rnd, int start, int end, ArrayList<Integer> exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.size());
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
    }



