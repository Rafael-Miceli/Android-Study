package com.unitteststudy.rafael.unitteststudy;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Rafael on 20/03/2015.
 */
public class UserAzureRepositoryTest {

    private Integer expectedTankCriticalLevel;
    private String expectedTankName;
    private Double expectedTankHeight;
    private String expectedClientName;
    private Client expectedClient;

    @Test
    public void test_Login_Callback_Return_The_ClientName_And_The_Tanks() throws Exception {
        String username = "rafael.miceli@hotmail.com";
        String password = "12345678";

        expectedDataToReturnAfterLogin();

        final JsonObject customUser = new JsonObject();
        customUser.add("Client", new JsonObject());

        WrappedMobileServiceJsonTable mockedMobileServiceJsonTable = mock(WrappedMobileServiceJsonTable.class);
        Intent intent = mock(Intent.class);
        Context context = mock(Context.class);
        GsonWrapper gson = mock(GsonWrapper.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((TableJsonOperationCallback) invocation.getArguments()[2]).onCompleted(customUser, null, null);
                return null;
            }
        }).when(mockedMobileServiceJsonTable).insert(any(JsonObject.class), any(ArrayList.class), any(TableJsonOperationCallback.class));

        when(gson.fromJson(any(JsonElement.class), eq(Client.class))).thenReturn(expectedClient);

        AccountInsertCallbackHandler accountInsertCallbackHandler = new AccountInsertCallbackHandler(context, intent, gson);

        UserAzureRepository sut = new UserAzureRepository(mockedMobileServiceJsonTable, accountInsertCallbackHandler);

        sut.getByUsernameAndPassword(username, password);

        assertExpectedData(accountInsertCallbackHandler);
    }

    private void expectedDataToReturnAfterLogin() {
        expectedClientName = "ClienteTeste1";
        //Cisterna
        expectedTankCriticalLevel = 50;
        expectedTankName = "Cisterna";
        expectedTankHeight = 7.00;

        expectedClient = new Client();
        expectedClient.setName(expectedClientName);

        Tank tank = new Tank();
        tank.setName(expectedTankName);
        tank.setCriticalLevel(expectedTankCriticalLevel);

        ArrayList<Tank> expectedTanks = new ArrayList<Tank>();


        expectedClient.setTanks(expectedTanks);
    }

    private void assertExpectedData(AccountInsertCallbackHandler accountInsertCallbackHandler) {
        assertThat(accountInsertCallbackHandler.user[0], IsNull.notNullValue());
        assertThat(accountInsertCallbackHandler.user[0].client.name, IsEqual.equalTo(expectedClientName));
        //assertThat(accountInsertCallbackHandler.user[0].client.getTanks()[0].getTankName(), IsEqual.equalTo(expectedTankName));
        //assertThat(accountInsertCallbackHandler.user[0].client.getTanks()[0].getTankCriticalLevel(), IsEqual.equalTo(expectedTankCriticalLevel));
    }
}
