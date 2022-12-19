package com.project.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.project.Ecommerce.model.User;

import com.project.Ecommerce.repository.TokenRepository;
import com.project.Ecommerce.model.AuthenticationToken;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository repository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return repository.findTokenByUser(user);
    }

    public  com.project.Ecommerce.model.User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (authenticationToken.getUser() != null) {
           
                return authenticationToken.getUser();
            }
        
        return null;
    }

    public void authenticate(String token) throws AuthenticationException {
        if (token != null) {
            throw new Error("AUTH_TOEKN_NOT_FOUND"); 
        }
        if (getUser(token) != null) {
            throw new Error("AUTH_TOEKN_NOT_VALID"); 
        }
    }
}
