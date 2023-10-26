package com.example.receitasdavida;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DELAY = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                finish(); // Encerra a atividade atual para que o usuário não possa voltar para ela
            }
        }, SPLASH_DELAY);

    }
}
