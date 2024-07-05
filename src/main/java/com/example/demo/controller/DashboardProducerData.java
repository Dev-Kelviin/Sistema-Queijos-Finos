package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.ProducerRepositoryCustom;
import com.example.demo.service.CertificateService;

@RestController
public class DashboardProducerData {

    private final ProducerRepositoryCustom producerRepositoryCustom;
    private final CertificateService certificateService;

    public DashboardProducerData(ProducerRepositoryCustom producerRepositoryCustom, CertificateService certificateService){
        this.producerRepositoryCustom = producerRepositoryCustom;
        this.certificateService = certificateService;
    }

    @GetMapping("/dashboardProducerDate/{id}") 
    public ModelAndView viewUsersAndView(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("dashboardProducerDate"); 
        modelAndView.addObject("producers", producerRepositoryCustom.getProducerInfoDashboard(id));
        modelAndView.addObject("certificates", certificateService.getListCertificatesByProducerId(id));
        return modelAndView;
    }
}
