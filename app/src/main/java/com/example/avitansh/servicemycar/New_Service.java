package com.example.avitansh.servicemycar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class New_Service extends AppCompatActivity{
    Spinner serviceType, carList;
    String carTypeOfService;
    String car;
    ArrayList<String> allCars;
    String[] allCarsStrArr;
    String formattedDate;
    Button buttonDate;
    DatabaseHelper databaseHelper;
    EditText notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__service);

        String[] typesOfServices ={"Oil Change","Tire Rotation", "Smog Check", "Car Wash",
        "Full Service","Engine Change","Brake Pads"};

         databaseHelper = new DatabaseHelper(this);
        allCars = databaseHelper.returnAllCarAndModel();
        allCars.add("");
        allCarsStrArr = new String[allCars.size()];
        allCarsStrArr = allCars.toArray(allCarsStrArr);

        serviceType = (Spinner) findViewById(R.id.spinnerTypeOfService);
        carList = (Spinner) findViewById(R.id.spinnerAllCars);
        notes = (EditText) findViewById(R.id.editTextnotes);
        buttonDate = (Button) findViewById(R.id.ButtonDate);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typesOfServices);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceType.setAdapter(dataAdapter);
        serviceType.setOnItemSelectedListener(new YourItemSelectedListenerCarName());

        ArrayAdapter<String> dataAdapterCarsList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, allCarsStrArr);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carList.setAdapter(dataAdapterCarsList);
        carList.setOnItemSelectedListener(new YourItemSelectedListenerCarList());



    }

    public void getDate(View view) {
        DialogFragment picker = new DatePickerFragment();
        FragmentManager fm = this.getFragmentManager();
        picker.show(fm,"datePicker");

    }

    public void goBacktoMain(View view) {
        Intent i = new Intent(this, MainHub.class);
        startActivity(i);
    }

    public void registerNewService(View view) {
        databaseHelper.insertCarService(car,formattedDate,carTypeOfService,notes.getText().toString());
        Toast.makeText(this,"Inserted", Toast.LENGTH_LONG).show();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private WeakReference<New_Service> mActivity;

        public interface TheListener{
            public void returnDate(String date);
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            mActivity = new WeakReference<New_Service>((New_Service) getActivity());
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(c.getTime());
            Toast.makeText(getActivity(),formattedDate,Toast.LENGTH_LONG).show();
            New_Service ns = mActivity.get();
            if(ns!=null){
                ns.formattedDate = formattedDate;
            }
        }
    }
    public class YourItemSelectedListenerCarName implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            carTypeOfService = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
            carTypeOfService="";

        }
    }
    public class YourItemSelectedListenerCarList implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            car = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView parent) {
            car="";

        }
    }
}
