package com.example.morellib.chooseyourlanguagedemo;

//Very simple android app introducing the topic of shared preferences, menu items, and Alert Dialogs!
//A simple alert pops up asking you to select a language. It then stores your response and doesn't ask you again.
//You can still change your langauge in the future with the Language Menu icon!

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //simple way to store data in an app
        sharedPreferences = this.getSharedPreferences("com.example.morellib.chooseyourlanguagedemo", Context.MODE_PRIVATE);

        //Sets our initial language to blank.
        String language = sharedPreferences.getString("language", "");

        //if our language is blank, run the alert dialog
        if (language == "") {
            changeLanguage();
        }
    }
    //alert dialog. sets the title, message, and two buttons. Each button puts the respective language in a string form to the "Langauge" Shared preferences.
    public void changeLanguage(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Language Selector")
                .setMessage("Please select your language")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("language", "English").apply();
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sharedPreferences.edit().putString("language", "Spanish").apply();

                    }
                })
                .show();

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

        //Listens for a click of our new menu item "langauge". If clicked, you get to re-select your language
        //noinspection SimplifiableIfStatement
        if (id == R.id.language) {
            changeLanguage();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
