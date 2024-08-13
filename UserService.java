package com.example.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    private BCryptPasswordEncoder passwordEncoder;


    public ResponseDTO<User> registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        return new ResponseDTO<>("User registered successfully", registeredUser, true);
    }

    public ResponseDTO<String> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if ( passwordEncoder.matches(password, user.getPassword())){
                return new ResponseDTO<>("Login successful");
            } else {
                return new ResponseDTO<>("Invalid credentials", null, false);
            }
        } else {
            return new ResponseDTO<>("User not found", null, false);
        }
    }


    public ResponseDTO<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setAddress(updatedUser.getAddress());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());

            User savedUser = userRepository.save(user);
            sendUpdateNotification(savedUser.getEmail());
            return new ResponseDTO<>("User updated successfully", savedUser, true);
        } else {
            return new ResponseDTO<>("User not found", null, false);
        }
    }

    private void sendUpdateNotification(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Profile Updated");
        message.setText("Your profile information has been updated successfully.");
        mailSender.send(message);
    }
}
