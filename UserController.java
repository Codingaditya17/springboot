package com.example.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<User>> registerUser(@RequestBody User user) {
        ResponseDTO<User> response = userService.registerUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> loginUser(@RequestBody LoginDTO loginDTO) {
        ResponseDTO<String> response = userService.loginUser(loginDTO.getEmailId(), loginDTO.getPassword());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO<User>> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        ResponseDTO<User> response = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO<String>> logoutUser() {
        // Logic for logout (e.g., invalidate session)
        return ResponseEntity.ok(new ResponseDTO<>("Logout successful", null, true));
    }

}
