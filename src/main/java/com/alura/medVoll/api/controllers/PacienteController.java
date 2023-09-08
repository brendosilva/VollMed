package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.domain.paciente.AtualizarPaciente;
import com.alura.medVoll.api.domain.paciente.CadastroPaciente;
import com.alura.medVoll.api.domain.paciente.DetalhamentoPaciente;
import com.alura.medVoll.api.domain.paciente.ListagemPaciente;
import com.alura.medVoll.api.domain.paciente.entidade.Paciente;
import com.alura.medVoll.api.domain.paciente.repositories.PacienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoPaciente> cadastro(@RequestBody @Valid CadastroPaciente dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoPaciente> detalhesPaciente(@PathVariable final Long id) {
        var paciente = repository.findById(id).get();
        return ResponseEntity.ok(new DetalhamentoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPaciente>> listagem(Pageable pageable) {
        var lista =  repository.findAll(pageable).map(ListagemPaciente::new) ;
        return ResponseEntity.ok(lista);
    }
    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoPaciente> atualizar(@RequestBody @Valid AtualizarPaciente dados) {

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizaInfo(dados);

        return ResponseEntity.ok(new DetalhamentoPaciente(paciente));

    }
}
