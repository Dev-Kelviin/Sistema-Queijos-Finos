package com.example.demo.dtos;

import java.time.LocalDate;

public class ProducerDto {
    private String name;
    private String cpf;
    private String phone1;
    private String cnpj;
    private String socialReason;
    private String phone2;
    private String cep;
    private String street;
    private int number;
    private String neighborhood;
    private String state;
    private String city;
    private String email;
    private LocalDate signatureDate;
    private LocalDate expirationDate;
    private String status;
    private LocalDate statusDate;
    private LocalDate simPoa;
    private LocalDate susaf;
    private LocalDate sisbi;
    private LocalDate seloArte;
    private LocalDate cif;
    private String observation;

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(LocalDate signatureDate) {
        this.signatureDate = signatureDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(LocalDate statusDate) {
        this.statusDate = statusDate;
    }

    public LocalDate getSimPoa() {
        return simPoa;
    }

    public void setSimPoa(LocalDate simPoa) {
        this.simPoa = simPoa;
    }

    public LocalDate getSusaf() {
        return susaf;
    }

    public void setSusaf(LocalDate susaf) {
        this.susaf = susaf;
    }

    public LocalDate getSisbi() {
        return sisbi;
    }

    public void setSisbi(LocalDate sisbi) {
        this.sisbi = sisbi;
    }

    public LocalDate getSeloArte() {
        return seloArte;
    }

    public void setSeloArte(LocalDate seloArte) {
        this.seloArte = seloArte;
    }

    public LocalDate getCif() {
        return cif;
    }

    public void setCif(LocalDate cif) {
        this.cif = cif;
    }
}
