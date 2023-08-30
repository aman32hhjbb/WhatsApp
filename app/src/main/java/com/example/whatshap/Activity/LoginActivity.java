package com.example.whatshap.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.whatshap.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        EditText editEmail = findViewById(R.id.LoginEmail);
        EditText editText = findViewById(R.id.LoginPassword);
        Button loginButton = findViewById(R.id.LoginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String password = editText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    if (email.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // All fields are filled, attempt authentication
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    // Login successful
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        String uid = user.getUid();
                                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                        intent.putExtra("UserId",uid);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    // Login failed
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(LoginActivity.this, "Authentication failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        findViewById(R.id.LoginSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                finish();
            }
        });
        findViewById(R.id.LoginForget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgetActivity.class));
            }
        });
    }
}
