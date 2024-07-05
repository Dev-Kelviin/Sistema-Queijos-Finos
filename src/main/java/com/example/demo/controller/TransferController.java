package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Transfer;
import com.example.demo.service.ProducerService;
import com.example.demo.service.TechnologyService;
import com.example.demo.service.TransferService;

@RestController
public class TransferController {

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private TransferService transferService;

    @Autowired
    private ProducerService producerService;

    @GetMapping("/transfer/{producerId}")
    public ModelAndView showScreen(@PathVariable Long producerId) {
        Transfer transfer = new Transfer();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("gerenciamentoTransferencias");
        modelAndView.addObject("transfer", transfer);
        modelAndView.addObject("technologies", technologyService.getListTechnology());
        modelAndView.addObject("transfers", producerService.getListTransfer(producerId));
        return modelAndView;
    }

    @GetMapping("/transfer/edit/{id}")
    public ModelAndView editTransfer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("gerenciamentoTransferencias");
        Transfer transfer = transferService.getTransferById(id);
        modelAndView.addObject("transfer", transfer);
        modelAndView.addObject("technologies", technologyService.getListTechnology());
        modelAndView.addObject("transfers", transferService.getAllTransfers());
        return modelAndView;
    }

    @PostMapping("/transfer/updateTransfer/{producerId}")
    public RedirectView updateTransfer(@ModelAttribute("transfer") Transfer transfer,@PathVariable Long producerId, RedirectAttributes attributes) {
        try {
            transferService.updateTransfer(transfer);
            attributes.addFlashAttribute("condition", "true");
        } catch (DataIntegrityViolationException e) {
            attributes.addFlashAttribute("mensagem", "Email já cadastrado no sistema: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("mensagem", "Erro: " + e.getMessage());
        }
        return new RedirectView("/transfer/"+ producerId);
    }

    @PostMapping("/transfer/register/{producerId}")
    public ModelAndView registerTransfer(@ModelAttribute Transfer transfer, @PathVariable Long producerId) {
        transfer.setProducer(producerService.getProducer(producerId));
        transferService.createTransfer(transfer);
        return new ModelAndView("redirect:/transfer/"+ producerId);
    }
}
