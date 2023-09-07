package com.alura.medVoll.api.domain.medico;

import com.alura.medVoll.api.domain.medico.entidade.Medico;
import com.alura.medVoll.api.domain.endereco.entidade.Endereco;

public record DetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public DetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
