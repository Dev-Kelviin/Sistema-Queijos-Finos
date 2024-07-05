package com.example.demo.entity.enums;

public enum TipoCertificado {
    SIMPOA("azul", "SIM/POA"),
    SUSAF("vermelho", "SUSAF"),
    SISBI("preto", "SISBI"),
    SELOARTE("cinza", "SELO ARTE"),
    CIF("amarelo", "CIF");

    private final String cssClass; // Classe CSS para o tipo de certificado
    private final String descricao; // Descrição do tipo de certificado

    TipoCertificado(String cssClass, String descricao) {
        this.cssClass = cssClass;
        this.descricao = descricao;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getDescricao() {
        return descricao;
    }
}

