package com.rinha.rinhabackend.pessoas;

import org.junit.Test;

import static org.junit.Assert.*;

public class PessoaTest {
    Pessoa pessoa;

    @org.junit.Before
    public void setUp() throws Exception {
        pessoa = new Pessoa();
    }

    @org.junit.Test
    public void isValid() {
        pessoa.setApelido("apelido");
        pessoa.setNome("nome");
        pessoa.setNascimento("2021-01-01");
        assertTrue(pessoa.validarPessoa());
    }

    @Test
    public void isValidFalseApelidoVazio() {
        pessoa.setApelido("");
        pessoa.setNome("");
        pessoa.setNascimento(null);
        assertFalse(pessoa.validarPessoa());
    }

    @org.junit.Test
    public void isValidDate() {
    }
}