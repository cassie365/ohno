package com.cassie365.ohno.objects;

/**
 * Class representing an ohno card
 * Some cards compel the next player to perform an action OR modify the play in some way
 */
public class Card {
    private String text;
    private String color;
    private int value;

    public Card(String text, String color, int value){
        this.text = text;
        this.color = color;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString(){
        return color+" "+text;
    }

}