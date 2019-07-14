package com.loogs.alc_4_0.phase1.challenge.android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Now we are going to add a call for up button.
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setBackgroundColor(Color.parseColor("#3359DE")); //#1433c4
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        Button aboutALC = findViewById(R.id.btn_about_alc);
        Button myProfile = findViewById(R.id.btn_my_profile);

        aboutALC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * Source: https://stackoverflow.com/questions/31104476/not-an-enclosing-class-error-android-studio/31104517
                 *
                 * You have to use existing activity context to start new activity,
                 * new activity is not created yet, and you cannot use its context
                 * or call methods upon it.
                 *
                 * 'not an enclosing class error' is thrown because of your usage of 'this' keyword.
                 * 'this' is a reference to the current object — the object whose method or
                 * constructor is being called. With 'this' you can only refer to any member of the
                 * current object from within an instance method or a constructor
                 */
                Intent intent = new Intent(MainActivity.this, AboutALCActivity.class);
                startActivity(intent);
            }
        });

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(intent);
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
