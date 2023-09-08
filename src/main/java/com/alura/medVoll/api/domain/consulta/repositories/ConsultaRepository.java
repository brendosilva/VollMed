package com.alura.medVoll.api.domain.consulta.repositories;

import com.alura.medVoll.api.domain.consulta.entidade.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
