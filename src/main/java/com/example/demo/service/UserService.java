package com.example.demo.service;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;


@Service
public class UserService {
   
    @Autowired
    private UserRepository userRepository;

    @Validated
    public User createUser(@Valid User user) {
        Objects.requireNonNull(user, "Usuário inválido. Verifique os campos obrigatórios.");
        return userRepository.save(user);
    }

    public List <User> getListUser(){
        List<User> users = userRepository.findAll();
        return users != null ? users : Collections.emptyList();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    

    public User updateUser(User newUser) {
        System.out.println(newUser.getId());
        Optional<User> optionalUser = userRepository.findById(newUser.getId());
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            // Copia os atributos do novo usuário para o usuário existente
            BeanUtils.copyProperties(newUser, existingUser, "id");
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado com o ID fornecido: " + newUser.getId());
        }
    }
}

