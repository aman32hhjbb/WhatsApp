package com.example.whatshap.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.whatshap.Models.UserModel;
import com.example.whatshap.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    EditText Name, Email, Password;
    TextView Login;
    AppCompatButton SignupButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        Name = findViewById(R.id.SignUpName);
        Email = findViewById(R.id.SignUpEmail);
        Password = findViewById(R.id.SignUpPass);
        Login = findViewById(R.id.SignUpLogin);
        SignupButton = findViewById(R.id.SignUpButton);

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    if (name.isEmpty()) {
                        Toast.makeText(SignupActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    } else if (email.isEmpty()) {
                        Toast.makeText(SignupActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SignupActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // All fields are filled, attempt signup
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    // Signup successful
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        String uid = user.getUid();
                                        UserModel userModel=new UserModel(name,uid,email);
                                   FirebaseDatabase.getInstance().getReference().child("Data").child("User").child(uid).child("Personal").setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void unused) {
                                           Toast.makeText(getApplicationContext(),"Account created",Toast.LENGTH_SHORT).show();
                                           Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                           intent.putExtra("UserId",uid);
                                           startActivity(intent);
                                           finish();
                                       }
                                   });

                                    }
                                } else {
                                    // Signup failed
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(SignupActivity.this, "Signup failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}
