package com.sansi.acerbilgisayar.budgetpack.Classes;

/**
 * Created by Acer Bilgisayar on 2.9.2017.
 */

public class City {
    private String name;


    public City(String name) {
        super();
        this.name = name;

    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
