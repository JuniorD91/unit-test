package com.unit.teste.controller;

import com.unit.teste.model.Pessoa;
import com.unit.teste.repositores.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Pessoa> getPessoaById(@RequestParam("id") Long id){
        return pessoaRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Pessoa pessoa){
        pessoaRepository.save(pessoa);
    }
}
