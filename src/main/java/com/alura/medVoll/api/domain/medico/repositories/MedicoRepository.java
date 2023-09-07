package com.alura.medVoll.api.domain.medico.repositories;

import com.alura.medVoll.api.domain.medico.entidade.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByActiveTrue(Pageable pageable);
}
