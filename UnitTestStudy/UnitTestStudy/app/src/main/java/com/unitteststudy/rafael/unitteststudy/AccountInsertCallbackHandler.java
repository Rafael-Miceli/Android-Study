package com.unitteststudy.rafael.unitteststudy;

import android.content.Context;
import android.content.Intent;

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

    public AccountInsertCallbackHandler(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCompleted(JsonObject jsonObject, Exception e, ServiceFilterResponse serviceFilterResponse) {
        if (e != null)
            return;
        else
        {
            String clientName = jsonObject.getAsJsonPrimitive("clientName").getAsString();

            User receivedUser = new User();
            Client client = new Client();
            client.Name = clientName;
            receivedUser.client = client;

            user[0] = receivedUser;

            intent.putExtra("clientName", clientName);
            context.sendBroadcast(intent);
        }
    }
}
