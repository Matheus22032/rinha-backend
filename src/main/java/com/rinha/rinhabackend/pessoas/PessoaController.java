package com.rinha.rinhabackend.pessoas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;


    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> inlcuirPessoa(@RequestBody Pessoa pessoaRequest){
        if(!pessoaRequest.validarPessoa())
            return ResponseEntity.unprocessableEntity().build();
        if (pessoaRepository.existsByApelido(pessoaRequest.getApelido()))
            return ResponseEntity.unprocessableEntity().build();


        pessoaRepository.save(pessoaRequest);
        return ResponseEntity.created(URI.create("/pessoas/"+pessoaRequest.getId().toString())).build();
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable String id){
        var pessoa = pessoaRepository.findById(UUID.fromString(id));
        if (pessoa.isEmpty())
            return ResponseEntity.notFound().build();
        var pessoaResp = pessoa.get();

        return ResponseEntity.ok(pessoaResp);
    }

    @GetMapping("contagem-pessoas")
    public ResponseEntity<String> contarPessoa(){
        String count = String.valueOf(pessoaRepository.count());
        return ResponseEntity.ok(count);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<Iterable<Pessoa>> buscarPorTermo(@RequestParam("t") String termo){
        if (termo == null || termo.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Iterable<Pessoa> pessoasEncontradas = pessoaRepository.findByCustomSearch(termo.toLowerCase());
        return ResponseEntity.ok(pessoasEncontradas);
    }
}
