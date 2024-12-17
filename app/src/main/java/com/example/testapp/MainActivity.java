package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Moving from one screen to the other i.e login to signup
        // Find the Text and set a click listener
        TextView signUpText = findViewById(R.id.signupText);
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate to SecondActivity
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        /// Moving to dashboard

        Button validationButton = findViewById(R.id.loginButton);
        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDashboard = new Intent(MainActivity.this, Dashboard.class);
                startActivity(moveToDashboard);

                /// Success Toast
                Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
            }
        });


        /// Login With the help of firebase email and password
         EditText emailField, passwordField;
         Button loginButton;
         FirebaseAuth firebaseAuth;


        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        firebaseAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                            // Redirecting to Dashboard
                            Intent moveToDashboard = new Intent(MainActivity.this,Dashboard.class);
                            startActivity(moveToDashboard);


                        } else {
                            Toast.makeText(this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
        });

    }
}