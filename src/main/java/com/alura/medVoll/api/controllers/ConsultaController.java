package com.alura.medVoll.api.controllers;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.consulta.DadosDetalhamentoConsulta;
import com.alura.medVoll.api.domain.consulta.services.AgendaConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultaService agenda;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agenda (@RequestBody @Valid AgendamentoConsulta consulta) {

       agenda.agendar(consulta);
        return  ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));

    }
}
