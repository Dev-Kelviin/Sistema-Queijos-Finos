package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public ModelAndView viewUsersAndView() {
        
        ModelAndView modelAndView = new ModelAndView("cadastroUsuario");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("users", getAllUsers());
        return modelAndView;
    }

    @PostMapping("/users/register")
    public RedirectView createUser(@ModelAttribute("user") User user, RedirectAttributes attributes) {
        
        
        try {
            userService.createUser(user);
            attributes.addFlashAttribute("condition", "true");
        } catch (DataIntegrityViolationException e) {
            attributes.addFlashAttribute("mensagem", "Email já cadastrado no sistema");
            attributes.addFlashAttribute("user2", user);

            System.out.println(user.getNameUser());
        }

        return new RedirectView("/users");
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/users/updateUser")
    public RedirectView updateUser(@ModelAttribute("user") User user , RedirectAttributes attributes) {

        try {
            userService.updateUser(user);
            attributes.addFlashAttribute("condition", "true");
        } catch (DataIntegrityViolationException e) {
            attributes.addFlashAttribute("mensagem", "Email ja cadastardo no sistema ");
            attributes.addFlashAttribute("user", user);
            
        }

        return new RedirectView("/users");
    }

    public List<User> getAllUsers() {
        try {
            return userService.getListUser();
        } catch (Exception e) {

            logger.error("Erro ao obter a lista de usuários: {}", e.getMessage());
            return Collections.emptyList();
        }
    }



}
