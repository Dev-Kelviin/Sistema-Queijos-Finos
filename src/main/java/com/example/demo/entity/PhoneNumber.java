package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name= "tb_phoneNumber")
public class PhoneNumber {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;
    
    
    public Long getId() {
        return id;
    }
    
    public String getPhone() {
        return phone;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }


    
}
