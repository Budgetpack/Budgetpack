package com.sansi.acerbilgisayar.budgetpack.Classes;

/**
 * Created by Acer Bilgisayar on 1.10.2017.
 */

public class Event {
    private String name;
    private String characteristic;
    private Long price;
    private boolean isSelected=false;

    public Event(String name, String characteristic, Long price){
        super();
        this.name= name;
        this.characteristic = characteristic;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCharacteristic() {
        return characteristic;

    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
