package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.dtos.ProducerFilterDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ProducerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getProducerInfo() {
        List<Object[]> results = entityManager.createQuery(
            "SELECT p.id, p.name, c.expirationDate, c.status " +
            "FROM Producer p " +
            "LEFT JOIN p.contract c")
        .getResultList();

        List<Map<String, Object>> producerInfoList = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> producerInfo = new HashMap<>();
            producerInfo.put("id", result[0]);
            producerInfo.put("nameProducer", result[1]);
            producerInfo.put("expirationDate", result[2] != null ? result[2] : "Sem contrato ativo");
            producerInfo.put("status", result[3]);
            producerInfoList.add(producerInfo);
        }
        return producerInfoList;
    }
   
    public List<Map<String, Object>> getProducerInfoDashboard(Long id) {
        String jpql = "SELECT p.id, p.name, p.socialReason, p.cpf, p.cnpj, ph.phone, p.observation, a.cep, a.neighborhood, a.street, a.city, a.state, a.number " +
                      "FROM Producer p " +
                      "INNER JOIN p.phoneNumbers ph " +
                      "LEFT JOIN p.address a " +
                      "WHERE p.id = :id";
    
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("id", id); 
    
        List<Object[]> results = query.getResultList();
    
        List<Map<String, Object>> producerInfoDashboard = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> producerInfo = new HashMap<>();
            producerInfo.put("id", result[0]);
            producerInfo.put("name", result[1]);
            producerInfo.put("socialReason", result[2]);
            producerInfo.put("cpf", result[3]);
            producerInfo.put("cnpj", result[4]);
            producerInfo.put("phone", result[5]);
            producerInfo.put("observation", result[6]);
            producerInfo.put("cep", result[7]);
            producerInfo.put("neighborhood", result[8]);
            producerInfo.put("street", result[9]);
            producerInfo.put("city", result[10]);
            producerInfo.put("state", result[11]);
            producerInfo.put("number", result[12]);
            producerInfoDashboard.add(producerInfo);
        }
    
        return producerInfoDashboard;
    }
    
    

    public List<Map<String, Object>> getProducerInfoFilter(ProducerFilterDto producerFilterDto) {
        StringBuilder jpqlBuilder = new StringBuilder();
        jpqlBuilder.append("SELECT p.id, p.name, c.expirationDate, c.status ")
                   .append("FROM Producer p ")
                   .append("INNER JOIN p.address a ")
                   .append("LEFT JOIN p.contract c ");

        if (producerFilterDto.getTechnologyName() != null && !producerFilterDto.getTechnologyName().isEmpty()) {
            jpqlBuilder.append("INNER JOIN p.transfers tr ")
                       .append("INNER JOIN tr.technology t ");
        }

        jpqlBuilder.append("WHERE 1=1 ");

        if (producerFilterDto.getNameProducer() != null && !producerFilterDto.getNameProducer().isEmpty()) {
            jpqlBuilder.append("AND LOWER(p.name) LIKE LOWER(:name) ");
        }

        if (producerFilterDto.getCity() != null && !producerFilterDto.getCity().isEmpty()) {
            jpqlBuilder.append("AND LOWER(a.city) LIKE LOWER(:city) ");
        }

        if (producerFilterDto.getStatus() != null && !producerFilterDto.getStatus().isEmpty()) {
            jpqlBuilder.append("AND c.status = :status ");
        }

        if (producerFilterDto.getTechnologyName() != null && !producerFilterDto.getTechnologyName().isEmpty()) {
            jpqlBuilder.append("AND t.name = :technologyName ");
        }

        TypedQuery<Object[]> query = entityManager.createQuery(jpqlBuilder.toString(), Object[].class);

        if (producerFilterDto.getNameProducer() != null && !producerFilterDto.getNameProducer().isEmpty()) {
            query.setParameter("name", "%" + producerFilterDto.getNameProducer() + "%");
        }

        if (producerFilterDto.getCity() != null && !producerFilterDto.getCity().isEmpty()) {
            query.setParameter("city", "%" + producerFilterDto.getCity() + "%");
        }

        if (producerFilterDto.getStatus() != null && !producerFilterDto.getStatus().isEmpty()) {
            query.setParameter("status", producerFilterDto.getStatus());
        }

        if (producerFilterDto.getTechnologyName() != null && !producerFilterDto.getTechnologyName().isEmpty()) {
            query.setParameter("technologyName", producerFilterDto.getTechnologyName());
        }

        List<Object[]> results = query.getResultList();

        List<Map<String, Object>> producerInfoList = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> producerInfo = new HashMap<>();
            producerInfo.put("id", result[0]);
            producerInfo.put("nameProducer", result[1]);
            producerInfo.put("expirationDate", result[2] != null ? result[2] : "Sem contrato ativo");
            producerInfo.put("status", result[3]);
            producerInfoList.add(producerInfo);
        }
        return producerInfoList;
    }

}
