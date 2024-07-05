package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.dtos.ProducerFilterDto;
import com.example.demo.service.ProducerListService;
import com.example.demo.service.TechnologyService;

@Controller
public class ProducerListController {

    private final ProducerListService producerListService;
    private final TechnologyService technologyService;

    public ProducerListController(ProducerListService producerListService, TechnologyService technologyService) {
        this.producerListService = producerListService;
        this.technologyService = technologyService;
    }

    @GetMapping("/producers")
    public ModelAndView listProducersView(@ModelAttribute("producerFilterDto") ProducerFilterDto producerFilterDto) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("producers");
        modelAndView.addObject("technologies", technologyService.getListTechnology());
        modelAndView.addObject("producerFilterDto", new ProducerFilterDto());
        modelAndView.addObject("producers", producerListService.getProducerInfo());
        return modelAndView;
    }

    @GetMapping("/filterProducer")
    public RedirectView filterProducerView(@ModelAttribute("producerFilterDto") ProducerFilterDto producerFilterDto,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("isFilter", "true");
        redirectAttributes.addFlashAttribute("producersFilter", producerListService.getProducerInfoFilter(producerFilterDto));

        return new RedirectView("/producers");
    }

}
