package com.example.finalprojectmobile1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
private MaterialButton open;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open=findViewById(R.id.open);
        LottieAnimationView lottie = findViewById(R.id.wizard);
        lottie.setSpeed(0.3f);
        lottie.resumeAnimation();
        MakeActionOpenButton();
        LottieAnimationView lie = findViewById(R.id.welcomev);
        lie.setSpeed(0.3f);
        lie.resumeAnimation();


    }

    private void  MakeActionOpenButton()
    {
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginScreen();
            }
        });
    }

    public  void openLoginScreen()
    {
       Intent OPENintent= new Intent(this, LoginActivity.class);
        startActivity(OPENintent);
    }

    public void openGame()
    {
        Intent OPENintent= new Intent(this, GameActivity.class);
        startActivity(OPENintent);
    }






}