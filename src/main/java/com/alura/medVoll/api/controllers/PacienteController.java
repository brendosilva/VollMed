package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.medico.DetalhamentoMedico;
import com.alura.medVoll.api.paciente.AtualizarPaciente;
import com.alura.medVoll.api.paciente.CadastroPaciente;
import com.alura.medVoll.api.paciente.DetalhamentoPaciente;
import com.alura.medVoll.api.paciente.ListagemPaciente;
import com.alura.medVoll.api.paciente.entidade.Paciente;
import com.alura.medVoll.api.paciente.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
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
