package com.alura.medVoll.api.domain.consulta.validacao;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import com.alura.medVoll.api.domain.medico.repositories.MedicoRepository;
import com.alura.medVoll.api.domain.paciente.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActiveValidation implements  ValidationAgendamentoConsulta {
    @Autowired
    private PacienteRepository repository;
    public void valida(AgendamentoConsulta dados){
        if (dados.idPaciente() == null) {
            return;
        }

        var pacienteIsActive = repository.findActiveById(dados.idPaciente());
        if (!pacienteIsActive) {
            throw new ValidacaoException("Consulta não pode ser agendado, pois paciente não esta ativo");
        }
    }
}
