package com.unitteststudy.rafael.unitteststudy;

import android.content.Context;
import android.content.Intent;

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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * Created by Rafael on 20/03/2015.
 */
public class UserAzureRepositoryTest {

    private Integer expectedTankCriticalLevel;
    private String expectedTankName;
    private Double expectedTankHeight;
    private Integer expectedTankCriticalLevel2;
    private String expectedTankName2;
    private Double expectedTankHeight2;
    private String expectedClient;

    @Test
    public void test_Login_Callback_Return_The_ClientName_And_The_Tanks() throws Exception {
        String username = "rafael.miceli@hotmail.com";
        String password = "12345678";

        expectedDataToReturnAfterLogin();

        final JsonObject customUser = new JsonObject();
        customUser.addProperty("clientName", expectedClient);



        WrappedMobileServiceJsonTable mockedMobileServiceJsonTable = mock(WrappedMobileServiceJsonTable.class);
        Intent intent = mock(Intent.class);
        Context context = mock(Context.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((TableJsonOperationCallback) invocation.getArguments()[2]).onCompleted(customUser, null, null);
                return null;
            }
        }).when(mockedMobileServiceJsonTable).insert(any(JsonObject.class), any(ArrayList.class), any(TableJsonOperationCallback.class));
        AccountInsertCallbackHandler accountInsertCallbackHandler = new AccountInsertCallbackHandler(context, intent);

        UserAzureRepository sut = new UserAzureRepository(mockedMobileServiceJsonTable, accountInsertCallbackHandler);

        sut.getByUsernameAndPassword(username, password);

        assertExpectedData(accountInsertCallbackHandler);


    }

    private void expectedDataToReturnAfterLogin() {
        expectedClient = "ClienteTeste1";

        //Cisterna
        expectedTankCriticalLevel = 50;
        expectedTankName = "Cisterna";
        expectedTankHeight = 7.00;
        //Caixa d'agua
        expectedTankCriticalLevel2 = 20;
        expectedTankName2 = "Caixa d'agua";
        expectedTankHeight2 = 4.00;
    }

    private void assertExpectedData(AccountInsertCallbackHandler accountInsertCallbackHandler) {
        assertThat(accountInsertCallbackHandler.user[0], IsNull.notNullValue());
        assertThat(accountInsertCallbackHandler.user[0].client.Name, IsEqual.equalTo(expectedClient));
        assertThat(accountInsertCallbackHandler.user[0].client.getTanks()[0].getTankName(), IsEqual.equalTo(expectedTankName));
        assertThat(accountInsertCallbackHandler.user[0].client.getTanks()[0].getTankHeight(), IsEqual.equalTo(expectedTankHeight));
        assertThat(accountInsertCallbackHandler.user[0].client.getTanks()[0].getTankCriticalLevel(), IsEqual.equalTo(expectedTankCriticalLevel));
    }
}
