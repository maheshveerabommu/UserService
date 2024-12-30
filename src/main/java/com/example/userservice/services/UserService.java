package com.example.userservice.services;

import com.example.userservice.models.Token;
import com.example.userservice.models.User;
import com.example.userservice.repos.TokenRepo;
import com.example.userservice.repos.UserRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepo tokenRepo;


    public UserService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepo= tokenRepo;
    }

    public User signup(String username, String email, String password) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }

    public Token login(String email, String password) {

        /*
        * Find the user by email
        * If the user is not found, throw UsernameNotFoundException
        * If the password is invalid, throw UsernameNotFoundException
        * Create a new token
        * Set the token value to a random alphanumeric string of length 10
        * Set the token expiryAt to the current time + 1 hour
        * Return the token
         */

        Optional<User> optinaluser = userRepo.findByEmail(email);

        if (optinaluser.isEmpty()) {
            throw new UsernameNotFoundException("User with " + email + " not found");
        }
        User user = optinaluser.get();

        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new UsernameNotFoundException("Invalid password");
        }

        Token token = new Token();
        token.setValue(RandomStringUtils.randomAlphanumeric(10));
        token.setUser(user);
        token.setExpiryAt(System.currentTimeMillis()+ 3600000);
        token.setDeleted(false);
        tokenRepo.save(token);
        return token;
    }

    public User validate(String token){

        Optional<Token> optinalToken=tokenRepo.
                findByValueAndDeletedAndExpiryAtGreaterThan(token,false,System.currentTimeMillis());
        if(optinalToken.isEmpty()){
            return null;
        }

        return optinalToken.get().getUser();
    }
}
