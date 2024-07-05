package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.enums.TipoStatusProduction;
import com.example.demo.repository.TransferRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.validation.annotation.Validated;
import com.example.demo.entity.Transfer;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public long countStatusProducing() {
        return transferRepository.countByTipoStatusProduction(TipoStatusProduction.PRODUCING);
    }

    public long countStatusComplementation() {
        return transferRepository.countByTipoStatusProduction(TipoStatusProduction.COMPLEMENTATION);
    }

    public long countStatusWithdrawal() {
        return transferRepository.countByTipoStatusProduction(TipoStatusProduction.WITHDRAWAL);
    }

    public long countStatusDisconnected() {
        return transferRepository.countByTipoStatusProduction(TipoStatusProduction.DISCONNECTED);
    }

    
    public long countProducingByTechnology(String technologyName) {
        return transferRepository.countByTechnologyNameAndTipoStatusProduction(technologyName, TipoStatusProduction.PRODUCING);
    }

    public long countComplementationByTechnology(String technologyName) {
        return transferRepository.countByTechnologyNameAndTipoStatusProduction(technologyName, TipoStatusProduction.COMPLEMENTATION);
    }

    public long countWithdrawalByTechnology(String technologyName) {
        return transferRepository.countByTechnologyNameAndTipoStatusProduction(technologyName, TipoStatusProduction.WITHDRAWAL);
    }

    public long countDisconnectedByTechnology(String technologyName) {
        return transferRepository.countByTechnologyNameAndTipoStatusProduction(technologyName, TipoStatusProduction.DISCONNECTED);
    }


    public void createTransfer(Transfer transfer) {

        transferRepository.save(transfer);
    }

    @Validated
    public Transfer updateTransfer(Transfer updateTransfer) {
        if (updateTransfer.getId() == null) {
            throw new IllegalArgumentException("ID da transferência não fornecido no objeto atualizado.");
        }

        Optional<Transfer> optionalTransfer = transferRepository.findById(updateTransfer.getId());

        if (optionalTransfer.isPresent()) {
            Transfer existingTransfer = optionalTransfer.get();
            existingTransfer.setTechnology(updateTransfer.getTechnology());
            existingTransfer.setTipoStatusProduction(updateTransfer.getTipoStatusProduction());
            existingTransfer.setStartDate(updateTransfer.getStartDate());
            existingTransfer.setCompletionDate(updateTransfer.getCompletionDate());
            return transferRepository.save(existingTransfer);
        } else {
            throw new IllegalArgumentException("Transfer não encontrada com o ID fornecido: " + updateTransfer.getId());
        }
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Transfer getTransferById(Long id) {
        return transferRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transferência não encontrada"));
    }
}
