package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Technology;
import com.example.demo.service.TechnologyService;

import ch.qos.logback.core.model.Model;
import jakarta.persistence.EntityNotFoundException;


@RestController
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @GetMapping("/tecnologias")
    public ModelAndView iniciarTela(Model model) {
        Technology technology = new Technology();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gerenciamentoTecnologias");
        modelAndView.addObject("technology", technology);
        modelAndView.addObject("report", technologyService.generateReportForAllTechnologies());
        return modelAndView;
    }

    @PostMapping("/tecnologias/cadastrar")
    public RedirectView createTechnology(@ModelAttribute("technology") Technology technology, RedirectAttributes attributes) {
        try {
            technologyService.createTechnology(technology);
            attributes.addFlashAttribute("condition", "condition");
        } catch (DataIntegrityViolationException e) {
            attributes.addFlashAttribute("mensagem", "Erro ao cadastrar tecnologia: " + e.getMessage());
        }
        return new RedirectView("/tecnologias");
    }

    @PostMapping("/technology/updateTechnology")
    public RedirectView updateTechnology(@ModelAttribute("technology") Technology technology, RedirectAttributes attributes) {

        try {
            technologyService.updateTechnology(technology);
            attributes.addFlashAttribute("condition", "true");
        } catch (DataIntegrityViolationException e) {
            attributes.addFlashAttribute("mensagem", "Email ja cadastardo no sistema "+e.getMessage());
        }

        return new RedirectView("/tecnologias");
    }


    @PutMapping("technology/{id}")
    public ResponseEntity<Void> changeTechnologyStatus(@PathVariable Long id) {
        try {
            System.out.println("O ID E:" + id);
            technologyService.changeTechnologyStatus(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/technologies/{technologyId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long technologyID) {
        technologyService.deleteTechnology(technologyID);
        return ResponseEntity.ok().build();
    }

}