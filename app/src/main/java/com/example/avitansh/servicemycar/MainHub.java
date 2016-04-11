package com.example.avitansh.servicemycar;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainHub extends FragmentActivity {
    Button newCar, newService, newGas, button4, button5, button6, button7, button9, button8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        newCar = (Button) findViewById(R.id.newCar);
        newService = (Button) findViewById(R.id.newService);
        newGas = (Button) findViewById(R.id.newGas);

        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        //button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        button7.setVisibility(View.INVISIBLE);
        button8.setVisibility(View.INVISIBLE);
        button9.setVisibility(View.INVISIBLE);

    }


    public void goToNewCar(View view) {
        Intent i = new Intent(this, New_Car.class);
        startActivity(i);
    }

    public void goToNewServices(View view) {
        Intent i = new Intent(this,New_Service.class);
        startActivity(i);
    }

    public void viewCars(View view) {
        Intent i = new Intent(this, View_Cars.class);
        startActivity(i);
    }
}
