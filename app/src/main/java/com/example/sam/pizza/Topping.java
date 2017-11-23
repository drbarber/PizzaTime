package com.example.sam.pizza;


public class Topping {

    //Private variables
    int _id;
    String _name;

    //Empty Constructor
    public Topping(){}

    //Constructor
    public Topping(int id, String name){
        this._id = id;
        this._name = name;
    }

    public Topping(String name){
        this._name = name;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }
}
