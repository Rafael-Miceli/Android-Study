package com.unitteststudy.rafael.unitteststudy;

/**
 * Created by Rafael on 19/03/2015.
 */
public class Client {

    public String name;
    private Tank[] tanks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tank[] getTanks() {
        return tanks;
    }

    public void setTanks(Tank[] tanks) {
        this.tanks = tanks;
    }
}
