package com.example.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Certificate;
import com.example.demo.entity.enums.TipoCertificado;
import com.example.demo.repository.CertificateRepository;

@Service
public class CertificateService {
   
    @Autowired
    private CertificateRepository certificateRepository; 
    
    public List<Certificate> getListCertificatesByProducerId(Long producerId) {
        // Find all certificates
        List<Certificate> certificates = certificateRepository.findAll();

        // Filter certificates associated with the given producerId
        List<Certificate> certificatesFiltered = certificates.stream()
                .filter(cert -> cert.getProducer() != null && cert.getProducer().getId().equals(producerId))
                .collect(Collectors.toList());

        // Return the filtered list, or an empty list if certificatesFiltered is null
        return certificatesFiltered != null ? certificatesFiltered : Collections.emptyList();
    }

    public long countCertificadoSIMPOA() {
    return certificateRepository.countByTipoCertificado(TipoCertificado.SIMPOA);

    }

    public long countCertificadoSUSAF() {
        return certificateRepository.countByTipoCertificado(TipoCertificado.SUSAF);
    
    }

    public long countCertificadoSISBI() {
        return certificateRepository.countByTipoCertificado(TipoCertificado.SISBI);
    
    }
    
    public long countCertificadoSELOARTE() {
        return certificateRepository.countByTipoCertificado(TipoCertificado.SELOARTE);
        
    }

    public long countCertificadoCIF() {
        return certificateRepository.countByTipoCertificado(TipoCertificado.CIF); //SIF
    
    }    
}