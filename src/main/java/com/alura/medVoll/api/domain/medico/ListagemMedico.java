package com.alura.medVoll.api.domain.medico;

import com.alura.medVoll.api.domain.medico.entidade.Medico;

public record ListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade

) {
    public ListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
