package com.unit.test.controller;

import com.unit.test.model.Pessoa;
import com.unit.test.repositores.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private PessoaRepository pessoaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaByPathVariable(@PathVariable("id") Long id){
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
        return Objects.isNull(pessoa) ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(pessoa.get());
    }

    @GetMapping("/byParam")
    public Optional<Pessoa> getPessoaByRequestParam(@RequestParam("id") Long id){
        return this.pessoaRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Pessoa pessoa){
        this.pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        this.pessoaRepository.deleteById(id);
    }

}
