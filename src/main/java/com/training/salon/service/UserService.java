package com.training.salon.service;


import com.training.salon.entity.Role;
import com.training.salon.entity.User;
import com.training.salon.repository.UserRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void updateProfile(User user, String username, String fullName, String password) {
        user.setUsername(username);
        user.setFullName(fullName);
        user.setPassword(passwordEncoder.encode(password));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.warn("Can`t save user " + user);
        }
    }

    public void updateRole(String role, User user) {
        user.setRole(Role.valueOf(role));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.warn("Can not save user " + user);
        }
    }

    public void saveNewUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
            log.info("User " + user.getEmail() + " was successfully registered.");
        } catch (Exception ex) {
            log.warn("User is already exists");
        }
    }


}
