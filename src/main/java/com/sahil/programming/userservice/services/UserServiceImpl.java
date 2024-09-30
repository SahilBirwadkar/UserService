package com.sahil.programming.userservice.services;

import com.sahil.programming.userservice.Repositories.UserRepository;
import com.sahil.programming.userservice.dtos.TokenRepository;
import com.sahil.programming.userservice.models.Token;
import com.sahil.programming.userservice.models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    private TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    @Override
    public User signup(String name, String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            // TODO: Throw an exception from here like UserAlreadyExists
            return null;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(bcryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            // TODO: Throw an exception that user does not exist
            return null;
        }

        User user = userOptional.get();

        if (!bcryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            // TODO: Throw an exception that password doesn't match
            return null;
        }

        Token token = createToken(user);
        return tokenRepository.save(token);

    }

    @Override
    public User validate(String tokenValue) {
        Optional<Token> tokenOptional = tokenRepository.findByValue(tokenValue);
        if (tokenOptional.isEmpty()) {
            // TODO: throw an exception that token is invalid
            return null;
        }

        Token token = tokenOptional.get();

        return token.getUser();
    }

    @Override
    public void logout(String token) {

    }

    private Token createToken(User user) {
        Token token = new Token();

        token.setUser(user);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, 30);

        Date dateAfter30Days = calendar.getTime();

        token.setExpiryAt(dateAfter30Days);
        token.setDeleted(false);

        return token;
    }
}
