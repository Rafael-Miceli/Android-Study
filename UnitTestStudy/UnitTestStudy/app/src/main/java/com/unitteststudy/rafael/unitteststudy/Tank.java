package com.unitteststudy.rafael.unitteststudy;

/**
 * Created by Rafael on 24/03/2015.
 */
public class Tank {

    private String Name;
    private Integer CriticalLevel;

    public String getName() {
        return Name;
    }

    public Integer getCriticalLevel() {
        return CriticalLevel;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setCriticalLevel(Integer CriticalLevel) {
        this.CriticalLevel = CriticalLevel;
    }
}
