package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.endereco.entidade.Endereco;
import com.alura.medVoll.api.medico.AtalizarMedico;
import com.alura.medVoll.api.medico.CadastroMedico;
import com.alura.medVoll.api.medico.DetalhamentoMedico;
import com.alura.medVoll.api.medico.ListagemMedico;
import com.alura.medVoll.api.medico.entidade.Medico;
import com.alura.medVoll.api.medico.repositories.MedicoRepository;
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
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoMedico> cadastro(@RequestBody @Valid final CadastroMedico dados, UriComponentsBuilder uriBuilder) {

        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoMedico(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoMedico> getMedico(@PathVariable  final Long id) {
        return ResponseEntity.ok(new DetalhamentoMedico(repository.findById(id).get()));
    }


    @GetMapping
    public ResponseEntity<Page<ListagemMedico>> listagem(Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoMedico> atualizacao(@RequestBody @Valid AtalizarMedico atualizarMedicos) {
        var medico = repository.getReferenceById(atualizarMedicos.id());
        medico.atualizarInfo(atualizarMedicos);

        return ResponseEntity.ok(new DetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable final Long id) {
       // exclusão fisica repository.deleteById(id);
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }
}
