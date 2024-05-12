package com.rinha.rinhabackend.pessoas;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pessoa",indexes = @Index(name = "idx_pessoa_apelido", columnList = "apelido"))
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "apelido", nullable = false, length = 32, unique = true)
    private String apelido;
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;
    @Column(name = "nascimento", nullable = false)
    private String nascimento;

    @Convert(converter = StringListConverter.class)
    @Column(name = "stack", columnDefinition = "text")
    private List<String> stack;

    @Column(name = "searchable", columnDefinition = "text")
    private String searchable;

    public Pessoa(){

    }
    public Pessoa(String apelido, String nome, String nascimento, List<String> stack) {
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }
    public boolean validarPessoa(){
        var apelidoValido = this.apelido != null && !this.apelido.isEmpty() && this.apelido.length() < 32;
        var nomeValido = this.nome != null && !this.nome.isEmpty() && this.nome.length() < 100;
        var nascimentoValido = this.nascimento !=null && this.isValidDate(nascimento);
        if (stack == null || stack.isEmpty())
            return (apelidoValido && nomeValido && nascimentoValido);
        System.out.println(stack);
        var stackValido = this.stack.stream().allMatch(value -> value != null && value.length() <32);
        return apelidoValido && nomeValido && nascimentoValido && stackValido;
    }

    public boolean isValidDate(String nascimento){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            LocalDate.parse(nascimento,formatter);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public String getNome() {
        return nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", apelido='" + apelido + '\'' +
                ", nome='" + nome + '\'' +
                ", nascimento='" + nascimento + '\'' +
                ", stack=" + stack +
                '}';
    }
}
