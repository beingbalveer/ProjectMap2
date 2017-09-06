package com.example.android.projectmap;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    LoginDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new LoginDatabaseHelper(this);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

    }

    public void login(View view) {

        String new_email = email.getText().toString();
        String new_password = password.getText().toString();


        boolean b = check(new_email,new_password);

        if (b)
            startActivity(new Intent(this,Home1.class));

    }


    boolean check(String email,String password)
    {
        if(email.length()==0)
        {
            Toast.makeText(this, "pleaase enter email", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!email.contains("@"))
        {
            Toast.makeText(this, "invalide email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length()<8) {
            Toast.makeText(this, "password must contain 8 character", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.matches(".*\\d.*"))
        {
            Toast.makeText(this,"password must conatain a number",Toast.LENGTH_SHORT).show();
            return false;
        }

        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = pattern.matcher(password);
        boolean b = m.find();
        if (!b)
        {
            Toast.makeText(this,"password must contain a special character",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
