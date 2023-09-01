package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.endereco.entidade.Endereco;
import com.alura.medVoll.api.medico.CadastroMedico;
import com.alura.medVoll.api.medico.entidade.Medico;
import com.alura.medVoll.api.medico.repositories.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid final CadastroMedico dados) {
        repository.save(new Medico(dados));
    }
}
