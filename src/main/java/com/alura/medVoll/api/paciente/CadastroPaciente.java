package com.alura.medVoll.api.paciente;

import com.alura.medVoll.api.endereco.DadosEndereco;

public record CadastroPaciente(
        String nome, String email, String telefone, String cpf, DadosEndereco endereco
) {
}
