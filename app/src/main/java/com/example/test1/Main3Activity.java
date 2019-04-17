package com.example.test1;

import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Main3Activity extends AppCompatActivity {
    private EditText phoneNumber, txtName;
    private Button buttonSignUp;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        txtName = (EditText)findViewById(R.id.editText);
        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        btnlogin = (Button)findViewById(R.id.btnlogin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = phoneNumber.getText().toString().trim();
                String name = txtName.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    phoneNumber.setError("Enter a valid mobile");
                    phoneNumber.requestFocus();
                    return;
                }

                Intent intent = new Intent(Main3Activity.this, VerifyPhone.class);
                intent.putExtra("mobile", mobile);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = phoneNumber.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    phoneNumber.setError("Enter a valid mobile");
                    phoneNumber.requestFocus();
                    return;
                }
            }
        });
    }

}
