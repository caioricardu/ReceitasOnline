package com.example.receitasdavida;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class login extends AppCompatActivity {

    private static final int REQUEST_CODE = 101010;
//    ImageView imageViewLogin;
//
//    private Executor executor;
//    private BiometricPrompt biometricPrompt;
//    private BiometricPrompt.PromptInfo promptInfo;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //imageViewLogin=findViewById(R.id.imgBiometria);

//            BiometricManager biometricManager = BiometricManager.from(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
//                case BiometricManager.BIOMETRIC_SUCCESS:
//                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
//                    break;
//                case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
//                    Toast.makeText(this, "Fingerprint sensor Not exist", Toast.LENGTH_SHORT).show();
//                    break;
//                case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
//                    Toast.makeText(this, "Sensor not avail or busy", Toast.LENGTH_SHORT).show();
//                    break;
//                case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
//                    // Prompts the user to create credentials that your app accepts.
//                    final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
//                    enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
//                            BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
//                    startActivityForResult(enrollIntent, REQUEST_CODE);
//                    break;
//                case BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED:
//                    break;
//            }
//            executor = ContextCompat.getMainExecutor(this);
//            biometricPrompt = new BiometricPrompt(login.this,
//                    executor, new BiometricPrompt.AuthenticationCallback() {
//                @Override
//                public void onAuthenticationError(int errorCode,
//                                                  @NonNull CharSequence errString) {
//                    super.onAuthenticationError(errorCode, errString);
//                    Toast.makeText(getApplicationContext(),
//                                    "Authentication error: " + errString, Toast.LENGTH_SHORT)
//                            .show();
//                }
//
//                @Override
//                public void onAuthenticationSucceeded(
//                        @NonNull BiometricPrompt.AuthenticationResult result) {
//                    super.onAuthenticationSucceeded(result);
//                    startActivity(new Intent(login.this,principal.class));
//                    Toast.makeText(getApplicationContext(),
//                            "Authentication succeeded!", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onAuthenticationFailed() {
//                    super.onAuthenticationFailed();
//                    Toast.makeText(getApplicationContext(), "Authentication failed",
//                                    Toast.LENGTH_SHORT)
//                            .show();
//                }
//            });
//
//            promptInfo = new BiometricPrompt.PromptInfo.Builder()
//                    .setTitle("Biometric login for my app")
//                    .setSubtitle("Log in using your biometric credential")
//                    .setNegativeButtonText("Use account password")
//                    .build();
//
//
//            imageViewLogin.setOnClickListener(view -> {
//                biometricPrompt.authenticate(promptInfo);
//            });
//        }
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


    }
//}
