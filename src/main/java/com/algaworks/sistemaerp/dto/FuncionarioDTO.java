package com.algaworks.sistemaerp.dto;

public class FuncionarioDTO {

    private Integer id;

    private String nome;

    private String cpf;

    public FuncionarioDTO(Integer id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
