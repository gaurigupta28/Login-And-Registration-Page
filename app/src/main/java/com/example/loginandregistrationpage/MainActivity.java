package com.example.loginandregistrationpage;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MainActivity extends AppCompatActivity {
    private EditText emailTextView, passwordTextView;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            mAuth = FirebaseAuth.getInstance();

            // initialising all views through id defined above
            emailTextView = findViewById(R.id.email);
            passwordTextView = findViewById(R.id.password);
        Button btn_login = findViewById(R.id.login);
        Button btn_signup = findViewById(R.id.signup);



            btn_login.setOnClickListener(view -> loginUserAccount());

        btn_signup.setOnClickListener(v -> {
            Intent intent
                    = new Intent(MainActivity.this,
                    SignupActivity.class);
            startActivity(intent);

        });
        }

        private void loginUserAccount() {
            String email, password;
            email = emailTextView.getText().toString();
            password = passwordTextView.getText().toString();

            // validations for input email and password
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(),
                        "Please enter email!!",
                        Toast.LENGTH_LONG)
                        .show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(),
                        "Please enter password!!",
                        Toast.LENGTH_LONG)
                        .show();
                return;
            }

            /* signin existing user */
            final Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(
                                        @NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),
                                                "Login successful!!",
                                                Toast.LENGTH_LONG)
                                                .show();


                                        Intent intent = new Intent(MainActivity.this, Welcome.class);
                                        startActivity(intent);
                                    } else {

                                        // sign-in failed
                                        Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
        }

}

