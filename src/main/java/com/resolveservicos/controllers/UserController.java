package com.resolveservicos.controllers;

import com.resolveservicos.entities.dto.LoginRecord;
import com.resolveservicos.entities.dto.RecoveryJWTTokenRecord;
import com.resolveservicos.entities.dto.UserRecord;
import com.resolveservicos.entities.model.User;
import com.resolveservicos.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJWTTokenRecord> authenticateUser(@RequestBody LoginRecord loginRecord){
        RecoveryJWTTokenRecord recoveryJWTTokenRecord = userService.authenticateUser(loginRecord);

        return ResponseEntity.ok(recoveryJWTTokenRecord);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserRecord userDTO){
        return ResponseEntity.ok(userService.create(userDTO));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
