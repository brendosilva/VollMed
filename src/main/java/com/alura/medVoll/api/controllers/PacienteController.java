package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.paciente.CadastroPaciente;
import com.alura.medVoll.api.paciente.entidade.Paciente;
import com.alura.medVoll.api.paciente.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid CadastroPaciente dados){
        repository.save(new Paciente(dados));
    }
}
