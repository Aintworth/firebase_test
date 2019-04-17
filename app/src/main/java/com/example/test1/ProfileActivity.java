package com.example.test1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView profileUid, profileEmail, profileName;
    private Button buttonLogout;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileUid = (TextView)findViewById(R.id.profileUid);
        profileEmail = (TextView)findViewById(R.id.profileEmail);
        profileName = (TextView)findViewById(R.id.profileName);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);



    }
    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String Uid = user.getUid();
            profileEmail.setText(email);
            profileUid.setText(Uid);
        }

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),
                        Main3Activity.class));
            }
        });
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mConditionRef = mRootRef.child("users").child("pekerja").child(user.getUid()).child("nama");

        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                profileName.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
