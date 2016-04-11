package com.example.avitansh.servicemycar;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class New_Car extends FragmentActivity {
    Spinner carName;
    Spinner carModelSpinner;
    String carNameStr, carModelStr;
    DatabaseHelper databaseHelper;
    EditText licensePlate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__car);
         databaseHelper = new DatabaseHelper(this);
        String[] carList = {"Bugatti", "Bentley", "BMW", "Buick", "Acura", "Audi",
                "Aston Martin", "Cadillac", "Chevrolet", "Chrysler", "Corvette", "Dodge",
                "Ferrari", "Fisker", "Ford", "GMC", "Honda", "Hyundai", "Hummer", "Infiniti", "Jeep",
                "Jaguar", "Kia", "Lexus", "Lamborghini", "Land Rover", "Lincoln", "Suzuki", "Mercedes Benz",
                "Mitsubishi", "Mini", "Mustang", "Nissan", "Porsche", "Ram", "Rolls Royce", "Tesla", "Toyota",
                "Volkswagen", "Subaru"};

        String[] carModel = {"Rav 4", "Sienna", "Civic", "CR-V", "Accord", "Pilot",
                "F-150", "RX 350", "Mazda CX-5", "Highlander", " Outback", "Tacoma", "Forester", "Cherokee", "" +
                "HR-V", "Camry", "Mustang", "Explorer", " Escape", "4Runner", "Rouge", "Edge",
                "Mazda 3", "Crosstrek", "Sorrento", "X5", "Corolla", " Camaro", "Silverado", "Prius", "Wrangler",
                "Equinox", "MDX", "BMW X3", "RDX", "Murano", "Q5", "Q7", "Tucson", "1500", "NX 200t", "Colorado",
                "Renegade", "C-Class", "Tundra", "E-Class"};

        carName = (Spinner) findViewById(R.id.spinnerCarName);
        carModelSpinner = (Spinner) findViewById(R.id.spinnerCarM);
        licensePlate = (EditText) findViewById(R.id.editTextLicense);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carName.setAdapter(dataAdapter);
        carName.setOnItemSelectedListener(new YourItemSelectedListenerCarName());


        ArrayAdapter<String> dataAdapterModel = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, carModel);
        dataAdapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelSpinner.setAdapter(dataAdapterModel);
        carModelSpinner.setOnItemSelectedListener(new YourItemSelectedListenerCarModel());

    }

    public void goBack(View view) {
        Intent i = new Intent(this, MainHub.class);
        startActivity(i);
    }

    public void registerCar(View view) {
        if(carNameStr.isEmpty()){
            Toast.makeText(this, "Car Name cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(carModelStr.isEmpty()){
            Toast.makeText(this, "Car Model cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(licensePlate.getText().toString().isEmpty()){
            Toast.makeText(this, "Car License cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }
        if(!databaseHelper.onlyOnePlate(licensePlate.getText().toString())){
            Toast.makeText(this, "License Plate already exists", Toast.LENGTH_LONG).show();

        }
        else {
            databaseHelper.insertCarData(carNameStr, carModelStr, licensePlate.getText().toString());
            Toast.makeText(this, "Congrats! Data has been entered!", Toast.LENGTH_LONG).show();
        }

    }


    public class YourItemSelectedListenerCarName implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            carNameStr = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
            carNameStr="";

        }
    }
    public class YourItemSelectedListenerCarModel implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            carModelStr = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
            carModelStr="";

        }
    }


}
