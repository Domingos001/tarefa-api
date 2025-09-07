package br.com.Pedro.tarefa.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity // Diz ao JPA que esta classe é uma tabela no banco de dados
public class Tarefa {

    @Id // Marca o campo 'id' como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Diz ao banco para gerar o valor do id automaticamente
    private Long id;

    private String nome;
    private LocalDate dataEntrega;
    private String responsavel;

    // Construtor vazio, obrigatório pelo JPA
    public Tarefa() {
    }

    // Getters e Setters (métodos para acessar e modificar os campos)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}