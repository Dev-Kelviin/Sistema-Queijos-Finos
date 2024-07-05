package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long>{

    List<Technology> findAllByActiveItemTrue();


}
