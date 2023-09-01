package com.alura.medVoll.api.medico.repositories;

import com.alura.medVoll.api.medico.entidade.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
}
