package com.example.demo.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.entity.Technology;
import com.example.demo.entity.enums.TipoStatusProduction;
import com.example.demo.repository.TechnologyRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.validation.Valid;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private EntityManager entityManager;

    @Validated
    public Technology createTechnology(@Valid Technology technology) {
        Objects.requireNonNull(technology, "Tecnologia inválida. Verifique os campos obrigatórios.");
        return technologyRepository.save(technology);
    }

    @Validated
    public Technology updateTechnology(Technology updatedTechnology) {
        // Verifica se o ID da tecnologia está presente no objeto atualizado
        if (updatedTechnology.getId() == null) {
            throw new IllegalArgumentException("ID da tecnologia não fornecido no objeto atualizado.");
        }

        Optional<Technology> optionalTechnology = technologyRepository.findById(updatedTechnology.getId());

        if (optionalTechnology.isPresent()) {
            Technology existingTechnology = optionalTechnology.get();

            // Atualiza o nome da tecnologia com o novo nome fornecido no objeto atualizado
            existingTechnology.setName(updatedTechnology.getName());

            return technologyRepository.save(existingTechnology);
        } else {
            throw new IllegalArgumentException(
                    "Tecnologia não encontrada com o ID fornecido: " + updatedTechnology.getId());
        }
    }

    public List<Technology> getListTechnology() {
        List<Technology> technologies = technologyRepository.findAll();
        return technologies != null ? technologies : Collections.emptyList();
    }


    public void changeTechnologyStatus(Long id) {

        Technology technology = technologyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tecnologia não encontrada com ID: " + id));

        technology.setActiveItem(!technology.getActiveItem());

        technologyRepository.save(technology);
    }

    public void deleteTechnology(Long technologyId) {
        technologyRepository.deleteById(technologyId);
    }

    public Map<Long, Map<String, Object>> generateReportForAllTechnologies() {

        Query query = entityManager.createNativeQuery(
                "SELECT t.id, t.name, tt.tipo_status_production, COUNT(*) " +
                        "FROM tb_technology t " +
                        "LEFT JOIN tb_transfer tt ON t.id = tt.technology_id " +
                        "WHERE t.active_item is true " +
                        "GROUP BY t.id, t.name, tt.tipo_status_production"
        );

        List<Object[]> results = query.getResultList();

        Map<Long, Map<String, Object>> report = new HashMap<>();
        results.forEach(row -> {
            Long technologyId = ((Number) row[0]).longValue();
            String technologyName = (String) row[1];
            Byte statusByte = (Byte) row[2];
            TipoStatusProduction status = (statusByte != null) ? TipoStatusProduction.values()[statusByte] : null;
            String statusName = (status != null) ? status.name() : null;
            Long count = ((Number) row[3]).longValue();

            report.computeIfAbsent(technologyId, id -> new HashMap<>())
                    .put("technologyName", technologyName);
            report.get(technologyId).put(statusName, count);
        });

        return report;
    }

    public List<String> getAllActiveTechnologyNames() {
        List<Technology> activeTechnologies = technologyRepository.findAllByActiveItemTrue();
        return activeTechnologies.stream()
                .map(Technology::getName) // Extrai apenas o nome da tecnologia
                .collect(Collectors.toList()); // Coleta os nomes em uma lista
    }

}
