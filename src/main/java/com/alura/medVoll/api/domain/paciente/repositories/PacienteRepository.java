package com.alura.medVoll.api.domain.paciente.repositories;

import com.alura.medVoll.api.domain.paciente.entidade.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("""
            select m.active
            from Paciente m
            where
            m.id = :id
            """)
    Boolean findActiveById(Long id);
}
