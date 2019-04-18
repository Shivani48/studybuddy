package com.example.studdybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText signinemail, signinpassword;
    Button signinbtn, signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signinemail = findViewById(R.id.email);
        signinpassword = findViewById(R.id.password);
        signinbtn = findViewById(R.id.signin);
        signupbtn = findViewById(R.id.signup);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(MainActivity.this, signup.class);
                startActivity(signup);

            }
        });

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = "login";
                String username = signinemail.getText().toString();
                String password = signinpassword.getText().toString();
                Login login = new Login(MainActivity.this);
                login.execute(type, username, password);

            }
        });

    }
}
