package com.alura.medVoll.api.domain.consulta.repositories;

import com.alura.medVoll.api.domain.consulta.entidade.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);


    boolean existsByMedicoIdAndDataBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
