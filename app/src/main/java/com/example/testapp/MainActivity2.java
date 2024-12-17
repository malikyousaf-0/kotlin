package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /// Moving to Dashboard
//        Button registerButton = findViewById(R.id.signUpButton);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent moveToDashboard = new Intent(MainActivity2.this,Dashboard.class);
//                startActivity(moveToDashboard);
//            }
//        });

        /// Moving Back To LoginScreen
        TextView loginText = findViewById(R.id.loginText);
        loginText.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent moveToLogInScreen = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(moveToLogInScreen);
            }

        });


        /// Firebase Authentication with email and password

         EditText emailField, passwordField;
         Button signUpButton;
         FirebaseAuth firebaseAuth;

        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        signUpButton = findViewById(R.id.signUpButton);
        firebaseAuth = FirebaseAuth.getInstance();


        signUpButton.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show();


                            // Redirecting to Dashboard
                            Intent moveToDashboard = new Intent(MainActivity2.this,Dashboard.class);
                            startActivity(moveToDashboard);


                        } else {
                            Toast.makeText(this, "Signup Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });



    }


}