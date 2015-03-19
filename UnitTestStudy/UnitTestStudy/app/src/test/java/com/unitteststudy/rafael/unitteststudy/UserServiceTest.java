package com.unitteststudy.rafael.unitteststudy;

import junit.framework.Assert;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void testLogin() throws Exception {
        UserService sut = new UserService();

        String username = "rafael.miceli@hotmail.com";
        String password = "12345678";
        User result = sut.login(username, password);

        assertThat(result.client, IsNull.notNullValue());
    }
}