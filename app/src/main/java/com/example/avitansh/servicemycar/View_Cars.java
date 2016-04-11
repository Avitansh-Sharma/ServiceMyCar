package com.example.avitansh.servicemycar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_Cars extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ArrayList<String> carList;
    String[] cars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__cars);

        ListView myListView = (ListView) findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(this);

        carList = databaseHelper.returnCars();

        cars = new String[carList.size()];
        cars = carList.toArray(cars);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,cars);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}
