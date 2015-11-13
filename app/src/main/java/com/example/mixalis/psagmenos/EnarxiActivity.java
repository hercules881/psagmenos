package com.example.mixalis.psagmenos;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import static android.media.MediaPlayer.create;

/**
 * Created by mixalis on 13/11/2015.
 */
public class EnarxiActivity extends Activity {
    ListView listView;
    String[] katigories = new String[]{
            "Γεωγραφία",
            "Ιστορία",
            "Αθλητικά",
            "Τεχνολογία",
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enarxi);

        CustomAdapter adapter = new CustomAdapter(this, katigories);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

}
