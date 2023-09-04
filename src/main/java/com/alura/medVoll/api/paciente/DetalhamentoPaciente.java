package com.alura.medVoll.api.paciente;

import com.alura.medVoll.api.endereco.entidade.Endereco;
import com.alura.medVoll.api.paciente.entidade.Paciente;

public record DetalhamentoPaciente(
        Long id, String nome, String email, String telefone, String cpf, Endereco endereco
) {
    public DetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
