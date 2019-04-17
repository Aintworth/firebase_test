package com.example.test1;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword =(EditText)findViewById(R.id.editTextPassword);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == buttonRegister){
                    RegisterUser();
                }
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == buttonLogin){
                    startActivity(new Intent(getApplicationContext(),
                            LoginActivity.class));
                }
            }
        });

    }


    public void RegisterUser(){
        String Email = editTextEmail.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)){
            Toast.makeText(this, "A Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered and logged in
                                //start Profile Activity here
                                Toast.makeText(Main2Activity.this, "registration successful",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            }else{
                                Toast.makeText(Main2Activity.this, "Couldn't register, try again",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }
}
