package com.alura.medVoll.api.domain.paciente;

import com.alura.medVoll.api.domain.paciente.entidade.Paciente;
import com.alura.medVoll.api.domain.endereco.entidade.Endereco;

public record DetalhamentoPaciente(
        Long id, String nome, String email, String telefone, String cpf, Endereco endereco
) {
    public DetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
