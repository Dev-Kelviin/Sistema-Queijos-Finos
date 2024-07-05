package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProducerFilterDto;
import com.example.demo.repository.ProducerRepositoryCustom;

@Service
public class ProducerListService {

    private final ProducerRepositoryCustom producerRepositoryCustom;

    public ProducerListService(ProducerRepositoryCustom producerRepositoryCustom) {
        this.producerRepositoryCustom = producerRepositoryCustom;
    }

    public List<Map<String, Object>> getProducerInfo() {
        List<Map<String, Object>> producerInfoList = producerRepositoryCustom.getProducerInfo();
        
        for (Map<String, Object> producerInfo : producerInfoList) {
            int contractStatus = checkContractExpiry(producerInfo);
            
            if (contractStatus == -1) {
                producerInfo.put("expirationDate", "contrato expirado");
                producerInfo.put("class", "expired");
            } else if (contractStatus == 1) {
                producerInfo.put("expirationDate", "próximo ao vencimento");
                producerInfo.put("class", "expiry");
            } else if (contractStatus == 0) {
                producerInfo.put("expirationDate", "contrato ativo");
                producerInfo.put("class", "active");
            } else if (contractStatus == -2) {
                producerInfo.put("expirationDate", "data inválida");
                producerInfo.put("class", "invalid-date");
            }
        }
    
        return producerInfoList;
    }
    
    public List<Map<String, Object>> getProducerInfoFilter(ProducerFilterDto producerFilterDto) {
        List<Map<String, Object>> producerInfoList = producerRepositoryCustom.getProducerInfoFilter(producerFilterDto);
        
        for (Map<String, Object> producerInfo : producerInfoList) {
            int contractStatus = checkContractExpiry(producerInfo);
            
            if (contractStatus == -1) {
                producerInfo.put("expirationDate", "contrato expirado");
                producerInfo.put("class", "expired");
            } else if (contractStatus == 1) {
                producerInfo.put("expirationDate", "próximo ao vencimento");
                producerInfo.put("class", "expiry");
            } else if (contractStatus == 0) {
                producerInfo.put("expirationDate", "contrato ativo");
                producerInfo.put("class", "active");
            } else if (contractStatus == -2) {
                producerInfo.put("expirationDate", "data inválida");
                producerInfo.put("class", "invalid-date");
            }
        }
    
        return producerInfoList;
    }

    public int checkContractExpiry(Map<String, Object> producerInfo) {
        Object expirationDateObj = producerInfo.get("expirationDate"); 
    
        try {
            LocalDate expirationDate = LocalDate.parse(expirationDateObj.toString());
            LocalDate currentDate = LocalDate.now();
      
            long differenceInDays = ChronoUnit.DAYS.between(currentDate, expirationDate);
            if (differenceInDays < 0) {
                return -1; // Contrato expirado
            } else if (differenceInDays <= 30) {
                return 1; // Contrato próximo ao vencimento
            } else {
                return 0; // Contrato ativo
            }
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            e.printStackTrace();
            return -2; // Data inválida
        }
    }
    

}
