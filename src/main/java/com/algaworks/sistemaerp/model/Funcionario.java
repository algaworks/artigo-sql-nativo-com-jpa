package com.algaworks.sistemaerp.model;

import com.algaworks.sistemaerp.dto.FuncionarioDTO;

import javax.persistence.*;

@NamedNativeQuery(name = "query.Funcionario.listar", query = "select * from funcionario", resultClass = Funcionario.class)
@NamedNativeQuery(name = "query.FuncionarioDTO.listar", query = "select * from funcionario", resultSetMapping = "mapeamento.construtor.FuncionarioDTO")
@SqlResultSetMapping(name = "mapeamento.Funcionario", entities = { @EntityResult(entityClass = Funcionario.class) })
@SqlResultSetMapping(name = "mapeamento.atributos.Funcionario",
        entities = { @EntityResult(entityClass = Funcionario.class,
                fields = { @FieldResult(column = "fun_id", name = "id"),
                        @FieldResult(column = "fun_nome", name = "nome"),
                        @FieldResult(column = "fun_cpf", name = "cpf") }) })
@SqlResultSetMapping(name = "mapeamento.construtor.FuncionarioDTO",
    classes = { @ConstructorResult(targetClass = FuncionarioDTO.class,
            columns = { @ColumnResult(name = "id", type = Integer.class),
                    @ColumnResult(name = "nome", type = String.class),
                    @ColumnResult(name = "cpf", type = String.class) }) })
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfq() {
        return cpf;
    }

    public void setCpfq(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcionario that = (Funcionario) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
