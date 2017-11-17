package com.example.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView textView = new TextView(this);
//        textView.setText("Wow!");
//        textView.setTextColor(Color.GREEN);
//        textView.setTextSize(40);

        setContentView(R.layout.activity_main);
//        setContentView(textView);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){}

//    @Override
//    public MenuInflater getMenuInflater() {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
}
