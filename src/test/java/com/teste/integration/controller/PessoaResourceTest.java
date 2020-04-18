package com.teste.integration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
public class PessoaResourceTest{

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    void setUpMockMvc() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    void deverRetornarStatus200SeCodigoDaPessoaExistir() throws Exception {

        this.mvc.perform(get("/pessoas/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deveRetornar404ParaRecursoPessoaNaoEncontrado() throws Exception {

        this.mvc.perform(get("/pessoas/{id}",100)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    void deveRetornar400ParaIdNull() throws Exception {

        this.mvc.perform(get("/pessoas/"+null)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveRetornarApenasUmaPessoaChamdoDeCarlosAntonioUsandoRequestParamStatus200() throws Exception{
        this.mvc.perform(get("/pessoas/byParam").param("id","1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarStatus404ParaRecursoPessoaNaoEncontrado() throws Exception{
        this.mvc.perform(get("/pessoas/byParam").param("id","9999")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarStatus200BuscandoTodosAsPessoas() throws Exception {

        this.mvc.perform(get("/pessoas/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deveRetornarStatus400QuandoPathVariableForInvalido() throws Exception {

        this.mvc.perform(get("/pessoas/{id}","s").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        this.mvc.perform(get("/pessoas/"+null).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveSalvarPessoaERetornarStatus201() throws Exception {
        this.mvc.perform(post("/pessoas/").content("{\"nome\" : \"teste 02\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRemoverPessoaERetornarStatus200()throws Exception{

        this.mvc.perform(delete("/pessoas/{id}",1))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void deveRetornar204SeIdNaoExistir()throws Exception{

        this.mvc.perform(delete("/pessoas/"+999))
                .andDo(print())
                .andExpect(status().isNoContent());

    }

}
