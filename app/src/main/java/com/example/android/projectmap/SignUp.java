package com.example.android.projectmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    LoginDatabaseHelper helper;
    EditText new_email;
    EditText new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        helper = new LoginDatabaseHelper(this);
        new_email = (EditText)findViewById(R.id.new_email);
        new_password = (EditText)findViewById(R.id.new_password);

    }

    public void signup(View view) {

        String email_str = new_email.getText().toString();
        String password_str = new_password.getText().toString();

        boolean c = check(email_str,password_str);
        if (c)
        {
            boolean b = helper.addValue(email_str,password_str);
            if (b)
            {
                Toast.makeText(this, "account created", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, "error in creating account", Toast.LENGTH_SHORT).show();
            }
        }

    }

    boolean check(String email,String password)
    {
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
