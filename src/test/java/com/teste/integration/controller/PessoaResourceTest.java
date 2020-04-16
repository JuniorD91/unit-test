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
    void deveRetornarStatus200BuscandoTodosAsPessoas() throws Exception {

        this.mvc.perform(get("/pessoas/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void deveRetornarStatus400QuandoPathVariableForInvalido() throws Exception {

        String pathVaribleInvalido = "s";

        this.mvc.perform(get("/pessoas/"+pathVaribleInvalido).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deveSalvarPessoaERetornarStatus201() throws Exception {
        this.mvc.perform(post("/pessoas/").content("{\"nome\" : \"teste 02\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

}
