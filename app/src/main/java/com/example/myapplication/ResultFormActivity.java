package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ResultFormActivity extends AppCompatActivity {
    TextView usernameTxt, passwordTxt, bodTxt, genderTxt, hobbiesTxt;
    Button exitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        usernameTxt = findViewById(R.id.usernameInput);
        passwordTxt = findViewById(R.id.passwordInput);
        bodTxt = findViewById(R.id.birthdateInput);
        genderTxt = findViewById(R.id.genderInput);
        hobbiesTxt = findViewById(R.id.hobbiesInput);
        exitBtn = findViewById(R.id.btnExit);

        Intent i2 = getIntent();
        String username = i2.getStringExtra("username");
        String password = i2.getStringExtra("password");
        String birthdate = i2.getStringExtra("birthdate");
        String gender = i2.getStringExtra("gender");
        String hobbies = i2.getStringExtra("hobbies");

        usernameTxt.setText(username);
        passwordTxt.setText(password);
        bodTxt.setText(birthdate);
        genderTxt.setText(gender);
        hobbiesTxt.setText(hobbies);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}