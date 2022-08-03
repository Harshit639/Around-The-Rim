package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.concurrent.TimeUnit;

public class verification extends AppCompatActivity {

    EditText pnumber, otp;
    EditText code;
    Button verify,resend,submit;
    String userphonenumber,verificationid;
    PhoneAuthProvider.ForceResendingToken token;
    FirebaseAuth fauth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks  callbacks;

    public void function(){
        Intent intent = new Intent(getApplicationContext(),feedtesting.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        pnumber= findViewById(R.id.pnum);
        code = findViewById(R.id.code);
        verify= findViewById(R.id.verify);
        resend = findViewById(R.id.resendbutton);
        submit= findViewById(R.id.submitbutton);
        otp= findViewById(R.id.editTextNumber3);
        fauth = FirebaseAuth.getInstance();
        resend.setEnabled(false);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pnumber.getText().toString().isEmpty()){
                    pnumber.setError("required");
                    return;
                }
                if(code.getText().toString().isEmpty()) {
                    pnumber.setError("required");
                    return;
                }
                userphonenumber= "+"+code.getText().toString()+pnumber.getText().toString();
                verifyphonenumber(userphonenumber);
                code.setVisibility(View.INVISIBLE);
                pnumber.setVisibility(View.INVISIBLE);
                verify.setVisibility(View.INVISIBLE);
                otp.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                resend.setVisibility(View.VISIBLE);
                Toast.makeText(verification.this, userphonenumber, Toast.LENGTH_SHORT).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.getText().toString().isEmpty()){
                    otp.setError("Enter OTP");
                    return;
                }
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid,otp.getText().toString());
                authenticateuser(credential);
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                authenticateuser(phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationid=s;
                token=forceResendingToken;
                resend.setEnabled(true);
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                resend.setEnabled(false);
            }
        };




    }

    public void verifyphonenumber(String phonenum){

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(fauth)
                .setActivity(this)
                .setPhoneNumber(phonenum)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    public  void authenticateuser(PhoneAuthCredential credential){
        fauth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(verification.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                ParseUser user = new ParseUser();
                user.setUsername(intent.getStringExtra("username"));
                user.setPassword(intent.getStringExtra("password"));

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Log.i("info", "success");
                            function();
                        }else{
                            Toast.makeText(verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(verification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}