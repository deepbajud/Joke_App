package com.example.jokeappbajud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setStatusBarColor(getResources().getColor(R.color.splash_status_bar_color));

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {

                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();

            }
        }, 3000);
    }
}