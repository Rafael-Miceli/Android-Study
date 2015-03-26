package com.unitteststudy.rafael.unitteststudy;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void test_Login() throws Exception {
        String username = "rafael.miceli@hotmail.com";
        String password = "12345678";
        User expectedUser = new User();
        expectedUser.client = new Client();
        expectedUser.client.name = "ClienteTeste1";

        UserCloudRepository mockedCloudRepo = mock(UserCloudRepository.class);
        when(mockedCloudRepo.getByUsernameAndPassword(username, password)).thenReturn(expectedUser);
        UserService sut = new UserService(mockedCloudRepo);

        User result = sut.login(username, password);

        assertThat(result.client.name, IsNull.notNullValue());
    }
}