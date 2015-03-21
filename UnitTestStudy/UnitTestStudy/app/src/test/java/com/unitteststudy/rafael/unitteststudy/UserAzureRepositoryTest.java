package com.unitteststudy.rafael.unitteststudy;

import android.content.Context;
import android.content.Intent;
import android.test.mock.MockContext;

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

    @Test
    public void test_Login_Callback() throws Exception {
        String username = "rafael.miceli@hotmail.com";
        String password = "12345678";
        String expectedClient = "ClienteTeste1";

        final JsonObject customUser = new JsonObject();
        customUser.addProperty("client",expectedClient);

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

        assertThat(accountInsertCallbackHandler.user[0].client.Name, IsNull.notNullValue());
        assertThat(accountInsertCallbackHandler.user[0].client.Name, IsEqual.equalTo(expectedClient));
    }
}
