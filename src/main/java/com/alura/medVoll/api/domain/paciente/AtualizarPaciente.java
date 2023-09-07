package com.alura.medVoll.api.domain.paciente;

import com.alura.medVoll.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record AtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
