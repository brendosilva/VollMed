package com.alura.medVoll.api.domain.consulta.validacao;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioValidation implements  ValidationAgendamentoConsulta {
    public void valida (AgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var verifyHorsOpen = dataConsulta.getHour() < 7;
        var verifyHorsToClose = dataConsulta.getHour() > 18;

        if (domingo || verifyHorsOpen || verifyHorsToClose ) {
            throw new ValidacaoException("Consulta fora do horario de funcionamento");
        }
    }
}
