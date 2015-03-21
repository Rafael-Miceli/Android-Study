package com.unitteststudy.rafael.unitteststudy;

/**
 * Created by Rafael on 18/03/2015.
 */
public class UserService {


    private UserCloudRepository userRepository;

    public UserService(UserCloudRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        
        User user = userRepository.getByUsernameAndPassword(username, password);

        return user;
    }

}
