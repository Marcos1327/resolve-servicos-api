package com.resolveservicos.services;

import com.resolveservicos.configurations.security.service.TokenService;
import com.resolveservicos.utils.emailMessage.EmailMessage;
import com.resolveservicos.entities.dto.LoginRecord;
import com.resolveservicos.entities.dto.RecoveryJWTTokenRecord;
import com.resolveservicos.entities.dto.UserRecord;
import com.resolveservicos.entities.model.Role;
import com.resolveservicos.entities.model.User;
import com.resolveservicos.entities.model.UserDetailsImpl;
import com.resolveservicos.enums.RoleName;
import com.resolveservicos.handlers.ResourceNotFoundException;
import com.resolveservicos.repositories.UserRepository;
import com.resolveservicos.utils.UserUtils;
import com.resolveservicos.utils.Util;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.resolveservicos.utils.UserUtils.convertRoleNameToRole;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private final UserRepository userRepository;
    private final Util util;
    private final UserUtils userUtils;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    private final EmailService emailService;

    public UserService(UserRepository userRepository, Util util, UserUtils userUtils, AuthenticationManager authenticationManager, TokenService tokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.util = util;
        this.userUtils = userUtils;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }


    public User create(UserRecord userDTO) throws MessagingException {
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
        user.setRoles(List.of(convertRoleNameToRole(userDTO)));

        userRepository.save(user);
        sendEmail(userDTO, user);
        return user;
    }

    public User updateUser(UserRecord userDTO, Long id) {
        User user = findById(id);
        User userLogged = getLoggedUser();

        if (!userLogged.getUserId().equals(user.getUserId())) {
           boolean isAdmin = userLogged.getRoles().stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMINISTRATOR));
            if (!isAdmin) {
                throw new IllegalArgumentException("You do not have permission to update this user.");
            }
        }

        if (!util.isNullOrEmpty(userDTO.name())) {
            user.setName(userDTO.name());
        }

        if (!util.isNullOrEmpty(userDTO.email())) {
            user.setEmail(userDTO.email());
        }

        if (!util.isNullOrEmpty(userDTO.password())) {
            user.setPassword(passwordEncoder().encode(userDTO.password()));
        }

        if (userDTO.role() != null && !util.isNullOrEmpty(userDTO.role().name())) {
            Role newRole = userUtils.convertRoleNameToRole(userDTO.role());
            updateRole(user, newRole);
        }

        try {
            logger.info("Saving user with ID: {}", id);
            userRepository.save(user);
            logger.info("User with ID: {} updated successfully", id);
        } catch (UnsupportedOperationException e) {
            logger.error("Failed to save user with ID: {}", id, e);
            throw e;
        }

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

    public RecoveryJWTTokenRecord authenticateUser(LoginRecord loginRecord) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRecord.email(), loginRecord.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJWTTokenRecord(tokenService.generateToken(userDetails));
    }


    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName).orElseThrow(() -> new ResourceNotFoundException("UserLogged not found with email: " + currentPrincipalName));
    }

    private void updateRole(User user, Role role) {
        List<Role> roles = new ArrayList<>(user.getRoles());
        roles.clear();
        roles.add(role);
        user.setRoles(roles);
    }

    private void sendEmail(UserRecord userDTO, User user) {
        CompletableFuture.runAsync(() -> {
            try {
                emailService.sendEmailWithAttachment(user.getEmail(), EmailMessage.createTitle(user), EmailMessage.messageToNewUser(user, userDTO.password()), null);
            } catch (MessagingException e) {
                logger.error("Failed to send email to user with email: {}", user.getEmail(), e);
            }
        });
    }
}
