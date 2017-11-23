package com.example.sam.pizza;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratedPizza extends AppCompatActivity {
    private TextView generatedPizzaList;
    private int toppingNumber;


    public void updateView(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_pizza);

        Intent intent = getIntent();
        toppingNumber = intent.getIntExtra("toppingNumber", 0);
        DatabaseHandler db = new DatabaseHandler(this);

        List<Topping> toppings = db.getAllToppings();
        dbSetup(db);

        generatedPizzaList = (TextView) findViewById(R.id.generatedPizzaText);

        generatePizzaList(generatedPizzaList, toppings, toppingNumber);
        //TextView pizzaList = (TextView) findViewById(R.id.generatedPizzaText);
        //updateView();

    }
    public void goBack(View v){
        this.finish();
    }

    public void dbSetup(DatabaseHandler db){
            if(db.getToppingsCount() == 0){
                db.addTopping(new Topping("Peperoni"));
                db.addTopping(new Topping("Sausage"));
                db.addTopping(new Topping(("Mushroom")));
                db.addTopping(new Topping("Onion"));
                db.addTopping(new Topping("Spinach"));
                db.addTopping(new Topping("Pineapple"));
                db.addTopping(new Topping("Bell Peppers"));
                db.addTopping(new Topping("Bacon"));
                db.addTopping(new Topping("Olives"));
                db.addTopping(new Topping("Chicken"));
                db.addTopping(new Topping("Tomatoes"));
                db.addTopping(new Topping("Anchovies"));
            }
    }

    public void generatePizzaList(TextView list, List<Topping> toppingList, int toppNumber){
        Collections.shuffle(toppingList);
        String text = new String();
        Topping topping = new Topping();
        for(int i = 0; i < toppNumber; i++){
            topping = toppingList.get(i);
            text += topping.getName() + "\n";
        }
        list.setText(text);
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    protected void onRestart(){
        super.onRestart();
    }
    protected void onResume(){
        super.onResume();
    }
    protected void onPause(){
        super.onPause();
    }
    protected void onStop(){
        super.onStop();
    }
    protected void onDestroy(){
        super.onDestroy();
    }

    public void findPizza(View v){
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=pizza");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void settingsPage(View v){
        Intent settingsPageIntent = new Intent(this, SettingsPage.class);
        this.startActivity(settingsPageIntent);
        overridePendingTransition(R.anim.fade_from_left, 0);
    }
}
