package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.consulta.DadosCancelamentoConsulta;
import com.alura.medVoll.api.domain.consulta.DadosDetalhamentoConsulta;
import com.alura.medVoll.api.domain.consulta.services.AgendaConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agenda (@RequestBody @Valid AgendamentoConsulta dados) {

       var consulta = agenda.agendar(dados);
        return  ResponseEntity.ok(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
