package com.example.sam.pizza;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SettingsPage extends AppCompatActivity {
    Spinner toppingSpinner;
    Button deleteToppingButton;
    Button addToppingButton;
    EditText addToppingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        toppingSpinner = (Spinner) findViewById(R.id.ToppingsSpinner);
        deleteToppingButton = (Button) findViewById(R.id.DeleteToppingsButton);
        addToppingButton = (Button) findViewById(R.id.SaveToppingButton);
        addToppingText = (EditText) findViewById(R.id.AddToppingTextView);

        //toppingSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        loadSpinnerData();

    }

    private void loadSpinnerData(){
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<Topping> toppings = db.getAllToppings();
        List<String> toppingNameList = new ArrayList<>();

        for (Topping topping : toppings ){
            toppingNameList.add(topping.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, toppingNameList);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        toppingSpinner.setAdapter(dataAdapter);
    }


    public void deleteTopping(View view) {
        String item = toppingSpinner.getSelectedItem().toString();
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        Topping topping = db.getToppingByName(item);
        db.deleteTopping(topping);
        loadSpinnerData();
    }

    public void addTopping(View view){
        String item = addToppingText.getText().toString();
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.addTopping(new Topping(item));
        loadSpinnerData();
    }
}
