package com.alura.medVoll.api.domain.consulta.services;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.consulta.DadosCancelamentoConsulta;
import com.alura.medVoll.api.domain.consulta.DadosDetalhamentoConsulta;
import com.alura.medVoll.api.domain.consulta.entidade.Consulta;
import com.alura.medVoll.api.domain.consulta.repositories.ConsultaRepository;
import com.alura.medVoll.api.domain.consulta.validacao.ValidationAgendamentoConsulta;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import com.alura.medVoll.api.domain.medico.entidade.Medico;
import com.alura.medVoll.api.domain.medico.repositories.MedicoRepository;
import com.alura.medVoll.api.domain.paciente.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidationAgendamentoConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(AgendamentoConsulta dados) {
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id medico informado não existe");
        }

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id paciente informado não existe");
        }

        validadores.forEach(v -> v.valida(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoException("Não existe medico disponivel nessa data");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);


        repository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Medico escolherMedico(AgendamentoConsulta dados){
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null ){
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhida!");
        }



        return medicoRepository.escolherMedicoAleatorioLivreData(dados.especialidade(), dados.data());

    }
}
