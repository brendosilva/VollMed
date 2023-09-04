package com.alura.medVoll.api.medico.repositories;

import com.alura.medVoll.api.medico.entidade.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByActiveTrue(Pageable pageable);
}
