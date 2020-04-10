package com.teste.deployment;

import com.teste.model.Pessoa;
import com.teste.repositores.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Pessoa> getPessoa(){
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> getPessoaByPathVariable(@PathVariable("id") Long id){
        return pessoaRepository.findById(id);
    }

    @GetMapping("/byParam")
    public Optional<Pessoa> getPessoaByRequestParam(@RequestParam("id") Long id){
        return pessoaRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody Pessoa pessoa){
        pessoaRepository.save(pessoa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        pessoaRepository.deleteById(id);
    }
}
