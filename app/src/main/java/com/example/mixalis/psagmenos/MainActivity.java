package com.example.mixalis.psagmenos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import Database.DatabaseConfig;
import Database.DatabaseHelper;

import static android.media.MediaPlayer.*;
import static com.example.mixalis.psagmenos.R.*;

public class MainActivity extends AppCompatActivity {
    private TextView enarxi;
    MediaPlayer mediaPlayer;
    ProgressBar progressBarq;





    @Override
    public void onBackPressed()
    {
        // super.onBackPressed();
        mediaPlayer.stop();
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.show();
        mediaPlayer = create(this, raw.back1);
        mediaPlayer.start();

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        new Thread(new Runnable() {
            @Override
            public void run() {

                DatabaseConfig.createDatabase(databaseHelper, new DatabaseConfig.DatabaseCreationListener() {
                    @Override
                    public void onDatabaseCreated() {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();                            }
                        });
                    }
                });

            }
        }).start();


        enarxi = (TextView) findViewById(id.enarxi);
        enarxi.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {


                Intent myIntent = new Intent(MainActivity.this, EnarxiActivity.class);
                MainActivity.this.startActivity(myIntent);
            }

        });

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
