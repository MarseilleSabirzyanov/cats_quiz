package com.sabirzyanov.kittens.service;

import com.sabirzyanov.kittens.Exceptions.UserNotFoundExceptionCustom;
import com.sabirzyanov.kittens.domain.User;
import com.sabirzyanov.kittens.repository.UserRepo;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepo userRepository;

    public UserDetailServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, UserNotFoundExceptionCustom, LockedException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UserNotFoundExceptionCustom("Пользователь не найден");
        }
        return user;
    }
}
