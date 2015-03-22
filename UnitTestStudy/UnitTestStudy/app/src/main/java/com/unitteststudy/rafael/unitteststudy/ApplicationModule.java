package com.unitteststudy.rafael.unitteststudy;

import android.content.Context;
import android.content.Intent;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rafael on 21/03/2015.
 */
@Module(library = true, injects = {MainActivity.class})
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public UserAzureRepository providesUserRepository() {
        MobileServiceClient mobileServiceClient = null;

        try {
            mobileServiceClient = new MobileServiceClient("https://arduinoapp.azure-mobile.net/", "QkTMsFHSEaNGuiKVsywYYHpHnIHMUB64", context);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WrappedMobileServiceJsonTable wrappedMobileServiceJsonTable = new WrappedMobileServiceJsonTable(mobileServiceClient.getTable("accounts"));
        AccountInsertCallbackHandler accountInsertCallbackHandler = new AccountInsertCallbackHandler(context, new Intent("Main"));

        return new UserAzureRepository(wrappedMobileServiceJsonTable, accountInsertCallbackHandler);
    }

    @Provides
    @Singleton
    public UserService providesUserService() {
        return new UserService(providesUserRepository());
    }

}
