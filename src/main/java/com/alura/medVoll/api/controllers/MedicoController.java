package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.domain.medico.AtalizarMedico;
import com.alura.medVoll.api.domain.medico.CadastroMedico;
import com.alura.medVoll.api.domain.medico.DetalhamentoMedico;
import com.alura.medVoll.api.domain.medico.ListagemMedico;
import com.alura.medVoll.api.domain.medico.entidade.Medico;
import com.alura.medVoll.api.domain.medico.repositories.MedicoRepository;
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
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
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
        return ResponseEntity.ok(new DetalhamentoMedico(repository.getReferenceById(id)));
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
