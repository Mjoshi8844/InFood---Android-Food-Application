package com.example.infood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneLogin extends AppCompatActivity {
    EditText entno;
    Button getotp;
    ProgressBar pgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

//        getSupportActionBar().hide();

        entno = findViewById(R.id.input_mobile);
        getotp = findViewById(R.id.btngtp);
        pgb = findViewById(R.id.progresssendotp);


        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!entno.getText().toString().trim().isEmpty()){
                    if((entno.getText().toString().trim()).length() == 10){

                        pgb.setVisibility(View.VISIBLE);
                        getotp.setVisibility(View.INVISIBLE);


                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + entno.getText().toString(), 60, TimeUnit.SECONDS, PhoneLogin.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                pgb.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                pgb.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                pgb.setVisibility(View.GONE);
                                getotp.setVisibility(View.VISIBLE);

                                Intent votp = new Intent(getApplicationContext(), VerifyOtp.class);
                                votp.putExtra("Mobile", entno.getText().toString());
                                votp.putExtra("backendotp", s);
                                startActivity(votp);
                            }
                        });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please Enter Correct Number!", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext() , "Enter Mobile Number!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}