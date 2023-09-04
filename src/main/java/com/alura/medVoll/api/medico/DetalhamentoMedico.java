package com.alura.medVoll.api.medico;

import com.alura.medVoll.api.endereco.entidade.Endereco;
import com.alura.medVoll.api.medico.entidade.Medico;

public record DetalhamentoMedico(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {
    public DetalhamentoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
