package com.rinha.rinhabackend.pessoas;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.UUID;

public interface PessoaRepository extends CrudRepository<Pessoa, UUID> {

    boolean existsByApelido(String apelido);
    @Query("SELECT p FROM Pessoa p WHERE lower(p.apelido) LIKE %:termo% OR lower(p.nome) LIKE %:termo% OR lower(p.nascimento) LIKE %:termo% OR lower(p.stack) LIKE %:termo%")
    Iterable<Pessoa> findByCustomSearch(@Param("termo") String termo);
}
