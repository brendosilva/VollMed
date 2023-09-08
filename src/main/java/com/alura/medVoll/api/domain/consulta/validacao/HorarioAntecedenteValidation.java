package com.alura.medVoll.api.domain.consulta.validacao;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedenteValidation implements  ValidationAgendamentoConsulta {

    public void valida (AgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferenca = Duration.between(agora, dataConsulta).toMinutes();
        if (diferenca < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedencia minima de 30minutos");
        }
    }
}
