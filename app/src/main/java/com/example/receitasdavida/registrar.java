package com.example.receitasdavida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class registrar extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nome = findViewById(R.id.campoCadastroNome);
        email = findViewById(R.id.campoCadastroEmail);
        senha = findViewById(R.id.campoCadastroSenha);

    }
    public void voltarLogin(View view){
        usuario a = new usuario();
        a.setNome(nome.getText().toString());
        a.setEmail(email.getText().toString());
        a.setSenha(senha.getText().toString());
    }



}