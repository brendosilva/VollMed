package com.alura.medVoll.api.domain.paciente.repositories;

import com.alura.medVoll.api.domain.paciente.entidade.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
