package com.alura.medVoll.api.domain.consulta.validacao;

import com.alura.medVoll.api.domain.consulta.AgendamentoConsulta;
import com.alura.medVoll.api.domain.exceptions.ValidacaoException;
import com.alura.medVoll.api.domain.medico.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActiveValidation implements  ValidationAgendamentoConsulta{

    @Autowired
    private MedicoRepository repository;
    public void valida(AgendamentoConsulta dados){
        if (dados.idMedico() == null) {
            return;
        }

        var medicoIsActive = repository.findActiveById(dados.idMedico());
        if (!medicoIsActive) {
            throw new ValidacaoException("Consulta não pode ser agendado, pois medico não esta ativo");
        }
    }
}
