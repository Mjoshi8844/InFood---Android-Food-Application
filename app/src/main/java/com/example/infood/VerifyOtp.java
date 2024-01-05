package com.example.infood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOtp extends AppCompatActivity {
    EditText in1, in2, in3, in4, in5, in6;
    TextView tv, rsl;
    String getotp;
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    SharedPreferences sharedpreferences;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

//        getSupportActionBar().hide();

        final Button botp;
        final ProgressBar pgb1;

        in1 = findViewById(R.id.inputotp1);
        in2 = findViewById(R.id.inputotp2);
        in3 = findViewById(R.id.inputotp3);
        in4 = findViewById(R.id.inputotp4);
        in5 = findViewById(R.id.inputotp5);
        in6 = findViewById(R.id.inputotp6);
        tv = findViewById(R.id.textmobile);
        botp = findViewById(R.id.btnotp);
        pgb1 = findViewById(R.id.progressverifyotp);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        email = sharedpreferences.getString(EMAIL_KEY, null);

        tv.setText(String.format("+91-%s", getIntent().getStringExtra("Mobile")));

        getotp = getIntent().getStringExtra("backendotp");

        botp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!in1.getText().toString().trim().isEmpty() && !in2.getText().toString().trim().isEmpty() && !in3.getText().toString().trim().isEmpty() && !in4.getText().toString().trim().isEmpty() && !in5.getText().toString().trim().isEmpty() && !in6.getText().toString().trim().isEmpty()) {
                    String entcodeotp = in1.getText().toString() + in2.getText().toString() + in3.getText().toString() + in4.getText().toString() +in5.getText().toString() + in6.getText().toString();

                    if(getotp!=null){
                        pgb1.setVisibility(View.VISIBLE);
                        botp.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotp, entcodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        pgb1.setVisibility(View.GONE);
                                        botp.setVisibility(View.VISIBLE);

                                        if(task.isSuccessful()){
                                            SharedPreferences.Editor editor = sharedpreferences.edit();

                                            // below two lines will put values for
                                            // email and password in shared preferences.
                                            editor.putString(EMAIL_KEY, tv.toString());
                                            editor.putString(PASSWORD_KEY, (in1.getText().toString()+in2.getText().toString()+in3.getText().toString()+in4.getText().toString()+in5.getText().toString()+in6.getText().toString()));

                                            // to save our data with key and value.
                                            editor.apply();

                                            Intent nxt = new Intent(getApplicationContext(), MainMenu.class);
                                            nxt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(nxt);
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(), "Enter Correct OTP!", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Please Check Internet Connection!", Toast.LENGTH_LONG).show();
                    }

                    //Toast.makeText(getApplicationContext(), "OTP Verify", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please Enter Complete OTP!", Toast.LENGTH_LONG).show();
                }
            }
        });

        otpmove();

        rsl = findViewById(R.id.resend);

        rsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + getIntent().getStringExtra("Mobile"), 60, TimeUnit.SECONDS, VerifyOtp.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newbackend, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        getotp = newbackend;
                        Toast.makeText(getApplicationContext(), "OTP Sent Successfully!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void otpmove() {

        in1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    in2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    in3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    in4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    in5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty())
                    in6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password != null) {
            Intent i = new Intent(getApplicationContext(), MainMenu.class);
            startActivity(i);
        }
    }
}