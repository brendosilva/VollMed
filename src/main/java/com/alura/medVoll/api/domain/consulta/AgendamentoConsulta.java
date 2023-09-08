package com.alura.medVoll.api.domain.consulta;

import com.alura.medVoll.api.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        Especialidade especialidade,

        @NotNull
        @Future
        LocalDateTime data
) {
}
