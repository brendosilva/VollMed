package com.alura.medVoll.api.domain.consulta.validacao;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.consulta.repositories.ConsultaRepository;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteConsultaAgendadaMesmoDiaValidation implements  ValidationAgendamentoConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void valida(AgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiConsultaNoMesmoDia =  repository.existsByMedicoIdAndDataBetwen(dados.idPaciente(),primeiroHorario,ultimoHorario);
        if (pacientePossuiConsultaNoMesmoDia) {
            throw new ValidacaoException("Você já possui consulta agendada no mesmo dia");
        }
    }
}
