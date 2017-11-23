package com.example.sam.pizza;

import java.util.List;

/**
 * Created by Sam on 11/18/2017.
 */

public class SavedPizza {

    int _id;
    String _toppings;

    public SavedPizza(){

    }

    public SavedPizza(int id, String toppings){
        this._id = id;
        this._toppings = toppings;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getToppings() { return this._toppings; }

    public  void setToppings(List<Topping> toppings) {

        for(Topping topping : toppings){
            this._toppings += topping.getName() + "\n";
        }

    }
}
