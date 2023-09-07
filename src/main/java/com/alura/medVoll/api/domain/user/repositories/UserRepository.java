package com.alura.medVoll.api.domain.user.repositories;

import com.alura.medVoll.api.domain.user.entidade.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String username);

}
