package com.example.demo.security;

import com.example.demo.exception.IncorrectCredentialsException;
import com.example.demo.exception.NoSuchUserExistsException;
import com.example.demo.model.admin.UserEntity;
import com.example.demo.model.admin.UserType;
import com.example.demo.persistence.UserRepository;
import com.example.demo.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String identifier = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity user = getUserByNameOrEmail(identifier, identifier);
        if (!AuthenticationUtil.verifyPassword(password, user.getPassword())) {
            throw new IncorrectCredentialsException("Invalid Credentials!");
        }
        return new UsernamePasswordAuthenticationToken(
                user.getUsername(), password, user.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UserEntity getUserByNameOrEmail(String name, String email) {
        Optional<UserEntity> user = userRepository.findByNameOrEmail(name, email);
        if (user.isEmpty()) {
            throw new NoSuchUserExistsException("No user was found");
        } else {
            return user.get();
        }
    }
}
