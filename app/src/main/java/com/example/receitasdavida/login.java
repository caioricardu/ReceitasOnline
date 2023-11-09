package com.example.receitasdavida;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.receitasdavida.controller.Notificacao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;


public class login extends AppCompatActivity {

    //login firebase
    private EditText edit_email, edit_senha;
    private Button button_Login;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos!","Login realizado com sucesso!"};
    //

    private static final int REQUEST_CODE = 101010; // biométria
    private EditText campoEmail, campoSenha;
    private Notificacao notificacao;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializar();
        IniciarComponenetes();


    }


    public void janelaCadastro(View view) {
        Intent intent = new Intent(this, registrar.class);
        startActivity(intent);
    }

    public void janelaPrincipal(View view) {
        Intent intent = new Intent(this, principal.class);
        finish();
        startActivity(intent);
    }

    public void loginPadrao() {

        String email = campoEmail.getText().toString().trim();
        String senha = campoSenha.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else if (email.equals("caioricardo@gmail.com") && senha.equals("123456")) {

            Toast.makeText(this, "Sucesso ao efetuar login", Toast.LENGTH_SHORT).show();

            notificacao.notificacao("Saudação", "Olá Caio, seja bem-vindo ao ReceitasOnline");

            Intent intent = new Intent(this, principal.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "E-mail ou senha inválidos", Toast.LENGTH_SHORT).show();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void compatibilidadeDispotivo() {

        BiometricManager controlador = BiometricManager.from(this);

        switch (controlador.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.BIOMETRIC_STRONG)) {

            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("ReceitasOnline Biometria", "O usuário pode autenticar com êxito");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.d("ReceitasOnline Biometria", "O usuário não pode autenticar porque não há hardware adequado");
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.d("ReceitasOnline Biometria", "O usuário não pode autenticar porque o hardware não está disponível");
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.d("Poupa+ Biométria", "O usuário não pode autenticar porque nenhuma credencial biométrica ou de dispositivo está registrada");

                final Intent adicionarBiometria = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                adicionarBiometria.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.BIOMETRIC_STRONG);
                startActivityForResult(adicionarBiometria, REQUEST_CODE);

                break;

            case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED:
                Log.d("ReceitasOnline Biometria", "O usuário não pode se autenticar porque uma vulnerabilidade de segurança foi descoberta com um ou mais sensores de hardware");
                break;

            case BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED:
                Log.d("ReceitasOnline Biometria", "O usuário não pode autenticar porque as opções especificadas são incompatíveis com a versão atual do Android");
                break;

            case BiometricManager.BIOMETRIC_STATUS_UNKNOWN:
                Log.d("ReceitasOnline Biometria", "Não é possível determinar se o usuário pode autenticar");
                break;

        }


        Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt = new BiometricPrompt((FragmentActivity) this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString); // erro de hardware
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result); // sucesso

                Intent intent = new Intent(login.this, principal.class);
                startActivity(intent);
                //alertas.mensagemLonga(R.string.sucesso_biometria);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed(); // rejeitado
                //alertas.mensagemLonga(R.string.falha_biometria);
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Título")
                .setSubtitle("Descrição")
                .setDescription("Descrição dois")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                .setConfirmationRequired(false)
                .build();

        biometricPrompt.authenticate(promptInfo);

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void inicializar() {

        campoEmail = findViewById(R.id.campoEmail);
        campoSenha = findViewById(R.id.campoSenha);

        ImageView impressaoDigital = findViewById(R.id.imgBiometria);
        impressaoDigital.setOnClickListener(v -> {
            compatibilidadeDispotivo();
        });

        notificacao = new Notificacao(this);

        Button acessar = findViewById(R.id.buttonLogin);
        acessar.setOnClickListener(v -> {
            loginPadrao();
        });
//firebase
        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    AutenticarUsuario(v);

                }

            }
        });
    }


    private void AutenticarUsuario(View view){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            telaPrincipal();
                        }
                    },3000);
                }else {
                    String erro;

                    try {
                        throw task.getException();
                    }catch (Exception e){
                        erro = "Erro ao logar usuário";
                    }
                    Snackbar snackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }});
    }

    private void telaPrincipal(){
        Intent intent = new Intent(login.this, principal.class);
        startActivity(intent);
        finish();
    }
    private void IniciarComponenetes(){
        edit_email = findViewById(R.id.campoEmail);
        edit_senha = findViewById(R.id.campoSenha);
        button_Login = findViewById(R.id.buttonLogin);
    }

}
