package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{

}
