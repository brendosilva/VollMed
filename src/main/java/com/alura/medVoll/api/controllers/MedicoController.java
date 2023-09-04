package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.endereco.entidade.Endereco;
import com.alura.medVoll.api.medico.AtalizarMedico;
import com.alura.medVoll.api.medico.CadastroMedico;
import com.alura.medVoll.api.medico.ListagemMedico;
import com.alura.medVoll.api.medico.entidade.Medico;
import com.alura.medVoll.api.medico.repositories.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<ListagemMedico> listagem(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(ListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizacao(@RequestBody @Valid AtalizarMedico atualizarMedicos) {
        var medico = repository.getReferenceById(atualizarMedicos.id());
        medico.atualizarInfo(atualizarMedicos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable final Long id) {
       // exclusão fisica repository.deleteById(id);
        var medico = repository.getReferenceById(id);
        medico.excluir();

    }
}
