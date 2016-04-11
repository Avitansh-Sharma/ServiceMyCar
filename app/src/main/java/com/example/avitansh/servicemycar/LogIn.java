package com.example.avitansh.servicemycar;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends FragmentActivity {
    EditText username;
    EditText password;

    Button register, login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPass);

        register = (Button) findViewById(R.id.buttonRegister);
        login = (Button) findViewById(R.id.buttonSignUp);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, Register.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHelper.checkPassword(username.getText().toString(), password.getText().toString()) == false){
                    Toast.makeText(LogIn.this, "Incorrect Login Credentials", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(LogIn.this, "Welcome: " + username.getText().toString(), Toast.LENGTH_LONG).show();

            }
        });

    }



}
