package com.sahil.programming.userservice.services;

import com.sahil.programming.userservice.models.Token;
import com.sahil.programming.userservice.models.User;


public interface UserService {
    public User signup(String name, String email, String password);

    public Token login(String email, String password);

    public User validate(String token);

    public void logout(String token);
}
