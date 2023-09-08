package com.alura.medVoll.api.domain.consulta.services;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.consulta.entidade.Consulta;
import com.alura.medVoll.api.domain.consulta.repositories.ConsultaRepository;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import com.alura.medVoll.api.domain.medico.entidade.Medico;
import com.alura.medVoll.api.domain.medico.repositories.MedicoRepository;
import com.alura.medVoll.api.domain.paciente.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(AgendamentoConsulta dados) {
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id medico informado não existe");
        }

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id paciente informado não existe");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = medicoRepository.findById(dados.idMedico()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data());


        repository.save(consulta);
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
