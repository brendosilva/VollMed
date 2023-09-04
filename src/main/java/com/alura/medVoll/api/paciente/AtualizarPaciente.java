package com.alura.medVoll.api.paciente;

import com.alura.medVoll.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record AtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {

}
