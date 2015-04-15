package com.unitteststudy.rafael.unitteststudy;

import java.util.ArrayList;

/**
 * Created by Rafael on 19/03/2015.
 */
public class Client {

    public String name;
    private ArrayList<Tank> tanks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }
}
