package com.example.studdybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class signup extends AppCompatActivity {

    EditText name,uni,phn,zip,email,psswd;
    Spinner mjr,lvl;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.name);
        uni=findViewById(R.id.university);
        phn=findViewById(R.id.phone);
        zip=findViewById(R.id.zipcode);
        email=findViewById(R.id.email);
        psswd=findViewById(R.id.password);

        mjr=findViewById(R.id.major);
        lvl=findViewById(R.id.level);
        signup=findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type="signup";
                String username=name.getText().toString().trim();
                String university=uni.getText().toString().trim();
                String phone=phn.getText().toString().trim();
                String zipcode=zip.getText().toString().trim();
                String mail=email.getText().toString().trim();
                String password=psswd.getText().toString().trim();
                String major=mjr.getSelectedItem().toString().trim();
                String level=lvl.getSelectedItem().toString().trim();
                Register reg = new Register(signup.this);
                reg.execute(type,username,university,phone,zipcode,mail,password,major,level);
            }
        });

    }
}
