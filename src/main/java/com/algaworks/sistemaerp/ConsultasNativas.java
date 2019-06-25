package com.algaworks.sistemaerp;

import com.algaworks.sistemaerp.dto.FuncionarioDTO;
import com.algaworks.sistemaerp.model.Funcionario;

import javax.persistence.*;
import java.util.List;

public class ConsultasNativas {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ConsultasNativas-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        primeiraConsultaJPQL(entityManager);
        especificandoColunas(entityManager);
        retornandoUmaEntidade(entityManager);
        retornandoUmaEntidadeEspecificandoColunas(entityManager);
        mapeandoResultadoComEntityResult(entityManager);
        mapeandoResultadoComEntityResultEspecificandoColunaEAtributo(entityManager);
        mapeandoResultadoComConstructorResult(entityManager);
        usandoNamedNativeQuery(entityManager);
        usandoNamedNativeQueryResultSetMapping(entityManager);
        consultaComProcedures(entityManager);
        consultaComProceduresResultSet(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void primeiraConsultaJPQL(EntityManager entityManager) {
        System.out.println(">>> private static void primeiraConsultaJPQL(EntityManager entityManager)");

        String sql = "select * from funcionario";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.forEach(arr -> System.out.println(String.format("%s, %s, %s", arr)));
    }

    private static void especificandoColunas(EntityManager entityManager) {
        System.out.println(">>> private static void especificandoColunas(EntityManager entityManager)");

        String sql = "select id, nome, cpf from funcionario";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.forEach(arr -> System.out.println(String.format("%s, %s, %s", arr)));
    }

    private static void retornandoUmaEntidade(EntityManager entityManager) {
        System.out.println(">>> private static void retornandoUmaEntidade(EntityManager entityManager)");

        String sql = "select * from funcionario";
        Query query = entityManager.createNativeQuery(sql, Funcionario.class);

        List<Funcionario> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    private static void retornandoUmaEntidadeEspecificandoColunas(EntityManager entityManager) {
        System.out.println(">>> private static void retornandoUmaEntidadeEspecificandoColunas(EntityManager entityManager)");

        String sql = "select id, nome, cpf from funcionario";
        Query query = entityManager.createNativeQuery(sql, Funcionario.class);

        List<Funcionario> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    private static void mapeandoResultadoComEntityResult(EntityManager entityManager) {
        System.out.println(">>> private static void mapeandoResultadoComEntityResult(EntityManager entityManager)");

        String sql = "select * from funcionario";
        Query query = entityManager.createNativeQuery(sql, "mapeamento.Funcionario");

        List<Funcionario> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    private static void mapeandoResultadoComEntityResultEspecificandoColunaEAtributo(EntityManager entityManager) {
        System.out.println(">>> private static void mapeandoResultadoComEntityResultEspecificandoColunaEAtributo(EntityManager entityManager)");

        // Simulando nome de colunas diferentes das propriedades da entidade.
        // O mapeamento com @SqlResultSetMapping vai ajustar isso.
        String sql = "select id fun_id, nome fun_nome, cpf fun_cpf from funcionario";
        Query query = entityManager.createNativeQuery(sql, "mapeamento.atributos.Funcionario");

        List<Funcionario> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    private static void mapeandoResultadoComConstructorResult(EntityManager entityManager) {
        System.out.println(">>> private static void mapeandoResultadoComConstructorResult(EntityManager entityManager)");

        String sql = "select id, nome, cpf from funcionario";
        Query query = entityManager.createNativeQuery(sql, "mapeamento.construtor.FuncionarioDTO");

        List<FuncionarioDTO> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    private static void usandoNamedNativeQuery(EntityManager entityManager) {
        System.out.println(">>> private static void usandoNamedNativeQuery(EntityManager entityManager)");

        Query query = entityManager.createNamedQuery("query.Funcionario.listar");

        List<Funcionario> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    private static void usandoNamedNativeQueryResultSetMapping(EntityManager entityManager) {
        System.out.println(">>> private static void usandoNamedNativeQueryResultSetMapping(EntityManager entityManager)");

        Query query = entityManager.createNamedQuery("query.FuncionarioDTO.listar");

        List<FuncionarioDTO> lista = query.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }

    public static void consultaComProcedures(EntityManager entityManager) {
        System.out.println(">>> private static void consultaComProcedures(EntityManager entityManager)");

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("nome_funcionario");

        storedProcedureQuery.registerStoredProcedureParameter("funcionario_id", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("funcionario_nome", String.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("funcionario_id", 1);

        storedProcedureQuery.execute();

        String nome = (String) storedProcedureQuery.getOutputParameterValue("funcionario_nome");

        System.out.println(nome);
    }

    public static void consultaComProceduresResultSet(EntityManager entityManager) {
        System.out.println(">>> private static void consultaComProceduresResultSet(EntityManager entityManager)");

        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("pesquisar_funcionarios", Funcionario.class);

        storedProcedureQuery.registerStoredProcedureParameter("termo_pesquisa", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("termo_pesquisa", "i");

        List<Funcionario> lista = storedProcedureQuery.getResultList();

        lista.forEach(f -> System.out.println(f.getNome()));
    }
}
