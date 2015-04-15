package com.unitteststudy.rafael.unitteststudy;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;

/**
 * Created by Rafael on 20/03/2015.
 */
public class AccountInsertCallbackHandler implements TableJsonOperationCallback {

    public final User[] user = {null};
    private Context context;
    private Intent intent;
    private GsonWrapper gson;

    public AccountInsertCallbackHandler(Context context, Intent intent, GsonWrapper gson) {
        this.context = context;
        this.intent = intent;
        this.gson = gson;
    }

    @Override
    public void onCompleted(JsonObject jsonObject, Exception e, ServiceFilterResponse serviceFilterResponse) {
        if (e != null)
            return;
        else
        {
            Client client = gson.fromJson(jsonObject.get("Client"), Client.class);

            User receivedUser = new User();
            receivedUser.client = client;

            user[0] = receivedUser;

            intent.putExtra("clientName", client.getName());
            context.sendBroadcast(intent);
        }
    }
}
