package com.unitteststudy.rafael.unitteststudy;

import android.util.Pair;

import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.MobileServiceJsonTable;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;

import java.util.List;

/**
 * Created by Rafael on 20/03/2015.
 */
public class WrappedMobileServiceJsonTable {

    private MobileServiceJsonTable mobileServiceJsonTable;

    public WrappedMobileServiceJsonTable(MobileServiceJsonTable mobileServiceJsonTable) {
        this.mobileServiceJsonTable = mobileServiceJsonTable;
    }

    public void insert(JsonObject element, List<Pair<String,String>> parameters, TableJsonOperationCallback callback) {
        mobileServiceJsonTable.insert(element, parameters, callback);
    }
}
