package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Certificate;
import com.example.demo.entity.enums.TipoCertificado;

public interface CertificateRepository extends JpaRepository<Certificate , Long>{

    long countByTipoCertificado(TipoCertificado TipoCertificado);
}