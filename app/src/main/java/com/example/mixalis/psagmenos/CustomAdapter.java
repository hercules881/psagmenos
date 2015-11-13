package com.example.mixalis.psagmenos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.text.InputType;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by mixalis on 13/11/2015.
 */
class CustomAdapter extends ArrayAdapter<String> {
    String send="";       //apothikeuei ola ta dedomena gia na ta steilei
    String timer="";      //apothikeuei to byte pou theloume na alaxoume
    String timitimer="";  // apothikeuei ti nea timi pou tha geinei to byte pou theloume na alaxoume
    String timitimer2=""; // apothikeei tin timi tou timitimer wste na tin xanasteilei an einai 2 byte i timi p theloume na alaxoume
    Integer position2=0;
    String s2="";
    Integer no2=0;





    private final Activity context;
    private  String[] itemname;
    private String [] kol;
    private String [] mike;
    private String m_Text = "";


    public CustomAdapter(EnarxiActivity context, String[] itemname) {
        super(context, R.layout.enarxi, itemname);
        // TODO Auto-generated constructor stub




        this.context=context;
        this.itemname=itemname;
        //this.kol=kol;
        //this.mike=mike;
        //this.sPort=sPort;
        //this.imgid=imgid;
    }




    public View getView(final int position, final View view, final ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.listviewenarxi, null, true);
        final TextView text1 = (TextView) rowView.findViewById(R.id.text);


        rowView.setClickable(true);  //test
        text1.setText(itemname[position]);



                    return rowView;

    };



}
