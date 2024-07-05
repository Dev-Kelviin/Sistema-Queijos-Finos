package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Controller
public class LoginController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/Login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Login/Login");
        return mv;
    }

    @PostMapping("/logInto")
    public ModelAndView logInto(@RequestParam("email") String email, @RequestParam("password") String password) {
        ModelAndView mv = new ModelAndView();

        User user = repository.findByEmailAndPassword(email, password);

        if (user != null) {
            mv.setViewName("redirect:/dashboardProducer");
        } else {
            // Se as credenciais forem inválidas, adicione uma mensagem de erro ao modelo
            mv.addObject("erro", "Credenciais inválidas. Por favor, tente novamente.");
            // Retorna para a mesma página de login
            mv.setViewName("Login/Login");
        }

        return mv;
    }

}

