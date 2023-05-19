package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private static final Logger logger = LogManager.getLogger("UserService");

    @Autowired
    private UserRepository userRepository;

    /**
     * Methode permettant de recupérer le user qui se log au format UserDetail pour son traitement d'authentification
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User connectedUser = userRepository.findByUsername(username);
        if (connectedUser == null) {
            logger.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        logger.info("User found");
        GrantedAuthority authority = new SimpleGrantedAuthority(connectedUser.getRole());
        return new org.springframework.security.core.userdetails.User(connectedUser.getUsername(),
                connectedUser.getPassword(), Collections.singletonList(authority));
    }

    public User getUserByUsername(String username) {
        logger.info("Getting user by username");
        return userRepository.findByUsername(username);
    }

    public void addUser(User user) {
        logger.info("Adding user");
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        logger.info("Getting all users");
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        logger.info("Updating user");
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        logger.info("Deleting user");
        userRepository.deleteById(id);
    }

    public Optional<User> getUserById(Integer id) {
        logger.info("Getting user by id");
        return userRepository.findById(id);
    }
}
