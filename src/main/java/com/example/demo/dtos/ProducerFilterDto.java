package com.example.demo.dtos;

import com.example.demo.entity.enums.TipoCertificado;
//import com.example.demo.entity.enums.TipoCertificado;

/* TUDO QUE ESTIVER COMMITADADO ESTA SENDO UTILIZADO EM OUTRA VERSÃO */

public class ProducerFilterDto {

    private String nameProducer;
    private String city;
    private String technologyName;
    private TipoCertificado tipoCertificado; 
    private String status;

    public String getNameProducer() {
        return nameProducer;
    }

    public void setNameProducer(String nameProducer) {
        this.nameProducer = nameProducer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

  
      public TipoCertificado gettipoCertificate() {
      return tipoCertificado;
      }
     
      public void settipoCertificate(TipoCertificado tipoCertificado) {
      this.tipoCertificado = tipoCertificado;
      }
     

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProducerFilterDto() {
    }

    public ProducerFilterDto(String nameProducer, String city, String technologyName,
            String status, TipoCertificado tipoCertificado) {
        this.nameProducer = nameProducer;
        this.city = city;
        this.technologyName = technologyName;
        this.tipoCertificado = tipoCertificado;
        this.status = status;
    }

}