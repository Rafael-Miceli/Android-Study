package com.unitteststudy.rafael.unitteststudy;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Created by Rafael on 26/03/2015.
 */
public class GsonWrapper {
    private Gson gson;

    public GsonWrapper() {
        gson = new Gson();
    }

    public GsonWrapper(Gson gson) {
        this.gson = gson;
    }

    public <T> T fromJson(JsonElement json, Class<T> tClass){
        return gson.fromJson(json, tClass);
    }
}
