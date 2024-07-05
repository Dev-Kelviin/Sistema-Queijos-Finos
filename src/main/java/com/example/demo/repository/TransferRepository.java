package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Transfer;
import com.example.demo.entity.enums.TipoStatusProduction;

public interface TransferRepository extends JpaRepository<Transfer , Long>{

    long countByTipoStatusProduction(TipoStatusProduction tipoStatusProduction);

    long countByTechnologyNameAndTipoStatusProduction(String technologyName, TipoStatusProduction producing);

}