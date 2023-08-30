package com.example.whatshap.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.text.TextUtils; // Import TextUtils
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.whatshap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgetActivity extends AppCompatActivity {
    EditText Email, NewPass, ConfirmPass;
    AppCompatButton changeButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        mAuth = FirebaseAuth.getInstance();

        Email = findViewById(R.id.ForgetEmail);
        NewPass = findViewById(R.id.ForgetNewPass);
        ConfirmPass = findViewById(R.id.ForgetConfirmPass);
        changeButton = findViewById(R.id.ForgetChanButton);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String newPass = NewPass.getText().toString();
                String confirmPass = ConfirmPass.getText().toString();

                // Check if any field is empty
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(newPass) || TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(ForgetActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if new password matches confirm password
                if (!newPass.equals(confirmPass)) {
                    Toast.makeText(ForgetActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgetActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ForgetActivity.this, "Password update failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(ForgetActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
