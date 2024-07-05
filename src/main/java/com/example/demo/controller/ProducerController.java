package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import com.example.demo.dtos.ProducerDto;
import com.example.demo.entity.*;
import com.example.demo.entity.enums.TipoCertificado;
import com.example.demo.repository.ProducerRepository;
import com.example.demo.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ProducerController {

    @Autowired
    private ProducerRepository producerRepository;

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/formProducer")
    public ModelAndView showForm() {
        Producer producer = new Producer();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("formProducer");
        modelAndView.addObject("producer", producer);
        return modelAndView;
    }

    /* @GetMapping("/producers") DESATIVADO POR CONTA DE MELHORIAS
    public ModelAndView showProducers(){
        Producer producer = new Producer();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("producers");
        modelAndView.addObject("producer", producer);
        modelAndView.addObject("producers", getAllProducers());
        return modelAndView;
    }*/

    @GetMapping("/producers/{producerId}")
    public ResponseEntity<Void> deleteProducer(@PathVariable Long producerId){
        System.out.println("Delete Producer " + producerId);
        producerRepository.deleteById(producerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/editProducer/update/{producerId}")
    public RedirectView updateProducer(
            @PathVariable Long producerId,
            @ModelAttribute ProducerDto producerDto
    ) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new NoSuchElementException("Producer not found with id: " + producerId));

        producer.setName(producerDto.getName());
        producer.setCpf(producerDto.getCpf());
        producer.setCnpj(producerDto.getCnpj());
        producer.setSocialReason(producerDto.getSocialReason());
        producer.setEmail(producerDto.getEmail());

        Contract contract = producer.getContract();
        contract.setStatus(producerDto.getStatus());
        contract.setSignatureDate(producerDto.getSignatureDate());
        contract.setExpirationDate(producerDto.getExpirationDate());
        contract.setStatusDate(producerDto.getStatusDate());

        List<Certificate> certificates = producer.getCertificates();
        certificates.clear();

        if (producerDto.getSimPoa() != null) {
            Certificate certificate = new Certificate();
            certificate.setData(producerDto.getSimPoa());
            certificate.setTipoCertificado(TipoCertificado.SIMPOA);
            certificate.setProducer(producer);
            certificates.add(certificate);
        }
        if (producerDto.getSusaf() != null) {
            Certificate certificate = new Certificate();
            certificate.setData(producerDto.getSusaf());
            certificate.setTipoCertificado(TipoCertificado.SUSAF);
            certificate.setProducer(producer);
            certificates.add(certificate);
        }
        if (producerDto.getSisbi() != null) {
            Certificate certificate = new Certificate();
            certificate.setData(producerDto.getSisbi());
            certificate.setTipoCertificado(TipoCertificado.SISBI);
            certificate.setProducer(producer);
            certificates.add(certificate);
        }
        if (producerDto.getSeloArte() != null) {
            Certificate certificate = new Certificate();
            certificate.setData(producerDto.getSeloArte());
            certificate.setTipoCertificado(TipoCertificado.SELOARTE);
            certificate.setProducer(producer);
            certificates.add(certificate);
        }
        if (producerDto.getCif() != null) {
            Certificate certificate = new Certificate();
            certificate.setData(producerDto.getCif());
            certificate.setTipoCertificado(TipoCertificado.CIF);
            certificate.setProducer(producer);
            certificates.add(certificate);
        }

        producer.setObservation(producerDto.getObservation());

        Address address = producer.getAddress();
        if (address == null) {
            address = new Address();
        }
        address.setStreet(producerDto.getStreet());
        address.setNeighborhood(producerDto.getNeighborhood());
        address.setState(producerDto.getState());
        address.setCep(producerDto.getCep());
        address.setCity(producerDto.getCity());
        address.setProducer(producer);
        address.setNumber(producerDto.getNumber());
        producer.setAddress(address);

        List<PhoneNumber> phoneNumbers = producer.getPhoneNumbers();
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
        }
        phoneNumbers.clear();

        PhoneNumber phone1 = new PhoneNumber();
        phone1.setPhone(producerDto.getPhone1());
        phone1.setProducer(producer);
        phoneNumbers.add(phone1);

        PhoneNumber phone2 = new PhoneNumber();
        phone2.setPhone(producerDto.getPhone2());
        phone2.setProducer(producer);
        phoneNumbers.add(phone2);

        producer.setPhoneNumbers(phoneNumbers);
        producer.setContract(contract);
        contract.setProducer(producer);
        producer.setCertificates(certificates);

        producerService.updateProducer(producer);

        return new RedirectView("/editProducer/" + producerId);
    }

    @GetMapping("/editProducer/{producerId}")
    public ModelAndView editProducer(@PathVariable Long producerId) {
        Producer producer = producerRepository.findById(producerId)
                .orElseThrow(() -> new NoSuchElementException("Producer not found with id: " + producerId));
        ProducerDto producerDto = new ProducerDto();

        producerDto.setName(producer.getName());
        producerDto.setCpf(producer.getCpf());
        producerDto.setCnpj(producer.getCnpj());
        producerDto.setSocialReason(producer.getSocialReason());
        producerDto.setEmail(producer.getEmail());

        producerDto.setSignatureDate(producer.getContract().getSignatureDate());
        producerDto.setExpirationDate(producer.getContract().getExpirationDate());
        producerDto.setStatusDate(producer.getContract().getStatusDate());

        List<Certificate> certificates = producer.getCertificates();

        LocalDate simPoaStr = null;
        for (Certificate certificate : certificates) {
            if (certificate.getTipoCertificado() == TipoCertificado.SIMPOA) {
                simPoaStr = certificate.getData();
                break;
            }
        }
        producerDto.setSimPoa(simPoaStr);

        LocalDate susafStr = null;
        for (Certificate certificate : certificates) {
            if (certificate.getTipoCertificado() == TipoCertificado.SUSAF) {
                susafStr = certificate.getData();
                break;
            }
        }
        producerDto.setSusaf(susafStr);

        LocalDate sisbiStr = null;
        for (Certificate certificate : certificates) {
            if (certificate.getTipoCertificado() == TipoCertificado.SISBI) {
                sisbiStr = certificate.getData();
                break;
            }
        }
        producerDto.setSisbi(sisbiStr);

        LocalDate seloArteStr = null;
        for (Certificate certificate : certificates) {
            if (certificate.getTipoCertificado() == TipoCertificado.SELOARTE) {
                seloArteStr = certificate.getData();
                break;
            }
        }
        producerDto.setSeloArte(seloArteStr);

        LocalDate cifStr = null;
        for (Certificate certificate : certificates) {
            if (certificate.getTipoCertificado() == TipoCertificado.CIF) {
                cifStr = certificate.getData();
                break;
            }
        }
        producerDto.setCif(cifStr);


        producerDto.setStatus(producer.getContract().getStatus());
        System.out.println("teste status: "+ producer.getContract().getStatus());
        System.out.println("teste status: "+ producerDto.getStatus());
        producerDto.setObservation(producer.getObservation());

        Address address = producer.getAddress();
        if (address != null) {
            producerDto.setStreet(address.getStreet());
            producerDto.setNeighborhood(address.getNeighborhood());
            producerDto.setState(address.getState());
            producerDto.setCep(address.getCep());
            producerDto.setCity(address.getCity());
            producerDto.setNumber(address.getNumber());
        }

        List<PhoneNumber> phoneNumbers = producer.getPhoneNumbers();
        if (phoneNumbers != null && !phoneNumbers.isEmpty()) {
            for (int i = 0; i < phoneNumbers.size(); i++) {
                if (i == 0) {
                    producerDto.setPhone1(phoneNumbers.get(i).getPhone());
                } else if (i == 1) {
                    producerDto.setPhone2(phoneNumbers.get(i).getPhone());
                }
            }
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("editProducer");
        model.addObject("producerDto", producerDto);
        model.addObject("producer", producer);

        return model;
    }

    private String formatDate(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(date);
        }
        return "";
    }

    @PostMapping("/formProducer/producer")
    public RedirectView createProducer(@ModelAttribute ProducerDto producerDto) {
        try {
            Producer producer = new Producer();
            Address address = new Address();
            PhoneNumber phone1 = new PhoneNumber();
            PhoneNumber phone2 = new PhoneNumber();

            System.out.println("teste"+ producerDto.getStatus());

            producer.setName(producerDto.getName());
            producer.setCpf(producerDto.getCpf());
            producer.setCnpj(producerDto.getCnpj());
            producer.setSocialReason(producerDto.getSocialReason());
            producer.setObservation(producerDto.getObservation());

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            producer.setEmail(producerDto.getEmail());

            List<Certificate> certificates = new ArrayList<>();

            Contract contract = new Contract();
            contract.setStatus(producerDto.getStatus());
            contract.setSignatureDate(producerDto.getSignatureDate());
            contract.setExpirationDate(producerDto.getExpirationDate());
            contract.setStatusDate(producerDto.getStatusDate());

            System.out.println("teste"+producerDto.getSimPoa());
            System.out.println(producerDto.getCif());
            System.out.println(producerDto.getSeloArte());

            if(producerDto.getSimPoa() != null){
                Certificate certificate1 = new Certificate();
                certificate1.setTipoCertificado(TipoCertificado.SIMPOA);
                certificate1.setData(producerDto.getSimPoa());
                certificates.add(certificate1);
                certificate1.setProducer(producer);
                certificates.add(certificate1);
            }
            if(producerDto.getSusaf() != null){
                Certificate certificate2 = new Certificate();
                certificate2.setTipoCertificado(TipoCertificado.SUSAF);
                certificate2.setData(producerDto.getSusaf());
                certificates.add(certificate2);
                certificate2.setProducer(producer);
                certificates.add(certificate2);
            }
            if(producerDto.getSisbi() != null){
                Certificate certificate3 = new Certificate();
                certificate3.setTipoCertificado(TipoCertificado.SISBI);
                certificate3.setData(producerDto.getSisbi());
                certificates.add(certificate3);
                certificate3.setProducer(producer);
                certificates.add(certificate3);

            }
            if(producerDto.getSeloArte() != null){
                Certificate certificate4 = new Certificate();
                certificate4.setTipoCertificado(TipoCertificado.SELOARTE);
                certificate4.setData(producerDto.getSeloArte());
                certificates.add(certificate4);
                certificate4.setProducer(producer);
                certificates.add(certificate4);

            }
            if(producerDto.getCif() != null){
                Certificate certificate5 = new Certificate();
                certificate5.setTipoCertificado(TipoCertificado.CIF);
                certificate5.setData(producerDto.getCif());
                certificates.add(certificate5);
                certificate5.setProducer(producer);
                certificates.add(certificate5);
            }

            address.setStreet(producerDto.getStreet());
            address.setNeighborhood(producerDto.getNeighborhood());
            address.setState(producerDto.getState());
            address.setCep(producerDto.getCep());
            address.setCity(producerDto.getCity());
            address.setProducer(producer);
            address.setNumber(producerDto.getNumber());

            phone1.setPhone(producerDto.getPhone1());
            phone2.setPhone(producerDto.getPhone2());
            phone1.setProducer(producer);
            phone2.setProducer(producer);

            List<PhoneNumber> phones = new ArrayList<>();
            phones.add(phone1);
            phones.add(phone2);

            producer.setPhoneNumbers(phones);
            producer.setAddress(address);
            producer.setContract(contract);
            contract.setProducer(producer);
            producer.setCertificates(certificates);
            producerService.addProducer(producer);

        } catch (DataIntegrityViolationException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new RedirectView("/producers");
    }

    private java.sql.Date parseDate(String dateString, DateTimeFormatter dateFormat) {
        if (dateString != null && !dateString.isEmpty()) {
            LocalDate date = LocalDate.parse(dateString, dateFormat);
            return java.sql.Date.valueOf(date);
        }
        return null;
    }

    public List<Producer> getAllProducers() {
        return producerService.getListProducers();
    }
}

