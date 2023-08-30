package com.example.whatshap.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.whatshap.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;

public class StartActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123; // Request code for Google Sign-In

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        AppCompatButton signInButton = findViewById(R.id.appCompatButton);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            // User is already signed in, proceed to the next activity
            moveToNextActivity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                // Signed in successfully, show authenticated UI.
                updateUI(account);
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                Log.w("GoogleSignIn", "signInResult:failed code=" + e.getStatusCode());
                updateUI(null);
            }
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            // User is signed in, you can access account information here
            String displayName = account.getDisplayName();
            String email = account.getEmail();
            Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show();
            moveToNextActivity();
        } else {
            // User is not signed in
        }
    }

    private void moveToNextActivity() {
        // Replace 'NextActivity.class' with the actual class name of your next activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional, to prevent the user from returning to this activity using the back button
    }
}
