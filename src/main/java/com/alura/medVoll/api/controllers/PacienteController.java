package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.paciente.AtualizarPaciente;
import com.alura.medVoll.api.paciente.CadastroPaciente;
import com.alura.medVoll.api.paciente.ListagemPaciente;
import com.alura.medVoll.api.paciente.entidade.Paciente;
import com.alura.medVoll.api.paciente.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid CadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<ListagemPaciente> listagem(Pageable pageable) {
        return repository.findAll(pageable).map(ListagemPaciente::new) ;
    }
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid AtualizarPaciente dados) {

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaInfo(dados);

    }
}
