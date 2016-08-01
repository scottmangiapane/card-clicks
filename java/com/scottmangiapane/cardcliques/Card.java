package com.scottmangiapane.cardcliques;

public class Card {
    public int drawable;
    public String color;
    public String shape;
    public String type;
    public int number;
    
    public String saveString() {
        return "" + drawable + " " + color + " " + shape + " " + type + " " + number;
    }
    
    public void loadString(String s) {
        String[] data = s.split(" ");
        drawable = Integer.parseInt(data[0]);
        color = data[1];
        shape = data[2];
        type = data[3];
        number = Integer.parseInt(data[4]);
    }
}
