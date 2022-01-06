package com.nimidev.bankingLedger.service;

import com.nimidev.bankingLedger.entity.User;
import com.nimidev.bankingLedger.error.UserNotFoundException;
import com.nimidev.bankingLedger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User readUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(User user, String id) {
        return userRepository.findById(id)
                .map(u -> {
                    u.setAddress(user.getAddress());
                    u.setCountry(user.getCountry());
                    u.setFullName(user.getFullName());
                    return userRepository.save(u);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
