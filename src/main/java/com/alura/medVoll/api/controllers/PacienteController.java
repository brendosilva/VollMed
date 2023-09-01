package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.paciente.CadastroPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @PostMapping
    public void cadastro(@RequestBody CadastroPaciente dados){

    }
}
