package com.unitteststudy.rafael.unitteststudy;

/**
 * Created by Rafael on 19/03/2015.
 */
public interface UserCloudRepository {
    User getByUsernameAndPassword(String username, String password);
}
