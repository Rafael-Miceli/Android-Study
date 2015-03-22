package com.unitteststudy.rafael.unitteststudy;

import dagger.ObjectGraph;

/**
 * Created by Rafael on 21/03/2015.
 */
public class SenaApp extends android.app.Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new ApplicationModule(this));
    }

    public ObjectGraph getObjectGraph(){
        return objectGraph;
    }
}
