package com.example.avitansh.servicemycar;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends FragmentActivity{
    EditText username;
    EditText password;

    Button register,returnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPass);

        register = (Button) findViewById(R.id.buttonRegisterUser);
        returnBack = (Button) findViewById(R.id.buttonReturnToLogin);

        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHelper.insertLoginData(username.getText().toString(),password.getText().toString())){
                    Toast.makeText(Register.this, "This", Toast.LENGTH_LONG).show();
                }
            }
        });

        returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, LogIn.class);
                startActivity(i);
            }
        });
    }

}
