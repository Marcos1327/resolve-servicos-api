package com.resolveservicos.services;

import com.resolveservicos.entities.dto.UserDTO;
import com.resolveservicos.entities.model.User;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.UserRepository;
import com.resolveservicos.utils.Util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Util util;
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository, Util util) {
        this.userRepository = userRepository;
        this.util = util;
    }


    public User create(UserDTO userDTO) {
        User user = new User();

        if (util.isNullOrEmpty(userDTO.name()) || util.isNullOrEmpty(userDTO.email()) || util.isNullOrEmpty(userDTO.password())) {
            throw new IllegalArgumentException("Name, email and password are required");
        }

        if (userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("User already exists with email: " + userDTO.email());
        }

        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(passwordEncoder().encode(userDTO.password()));
        user.setCreatedAt(LocalDate.now());

        userRepository.save(user);

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return findById(id);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public void deleteUser(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

}
