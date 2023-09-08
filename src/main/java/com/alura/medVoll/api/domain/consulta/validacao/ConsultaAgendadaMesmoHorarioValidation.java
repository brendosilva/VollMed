package com.alura.medVoll.api.domain.consulta.validacao;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.consulta.repositories.ConsultaRepository;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaAgendadaMesmoHorarioValidation implements  ValidationAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void valida(AgendamentoConsulta dados) {
        var medicoPossuiConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (medicoPossuiConsultaNoMesmoHorario) {
            throw new ValidacaoException("Esse horario já possui consulta");
        }
    }
}
