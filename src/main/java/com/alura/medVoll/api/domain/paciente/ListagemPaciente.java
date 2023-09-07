package com.alura.medVoll.api.domain.paciente;

import com.alura.medVoll.api.domain.paciente.entidade.Paciente;

public record ListagemPaciente(
        Long id, String nome, String email, String telefone, String cpf
) {
    public ListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf());

    }
}
