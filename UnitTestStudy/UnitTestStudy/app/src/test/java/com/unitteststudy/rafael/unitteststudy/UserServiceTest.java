package com.unitteststudy.rafael.unitteststudy;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import android.test.*;
import android.test.mock.MockContext;
import android.util.Pair;

import com.google.gson.JsonObject;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceJsonTable;
import com.microsoft.windowsazure.mobileservices.TableJsonOperationCallback;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void test_Login() throws Exception {
        String username = "rafael.miceli@hotmail.com";
        String password = "12345678";
        User expectedUser = new User();
        expectedUser.client = new Client();
        expectedUser.client.Name = "ClienteTeste1";

        UserCloudRepository mockedCloudRepo = mock(UserCloudRepository.class);
        when(mockedCloudRepo.getByUsernameAndPassword(username, password)).thenReturn(expectedUser);
        UserService sut = new UserService(mockedCloudRepo);

        User result = sut.login(username, password);

        assertThat(result.client.Name, IsNull.notNullValue());
    }
}