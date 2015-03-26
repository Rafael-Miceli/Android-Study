package com.unitteststudy.rafael.unitteststudy;

/**
 * Created by Rafael on 24/03/2015.
 */
public class Tank {

    private String name;
    private Double height;
    private Integer criticalLevel;

    public String getTankName() {
        return name;
    }

    public Double getTankHeight() {
        return height;
    }

    public Integer getTankCriticalLevel() {
        return criticalLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public void setCriticalLevel(Integer criticalLevel) {
        this.criticalLevel = criticalLevel;
    }
}
