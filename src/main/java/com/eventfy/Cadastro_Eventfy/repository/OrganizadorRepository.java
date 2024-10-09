package com.eventfy.Cadastro_Eventfy.repository;

import com.eventfy.Cadastro_Eventfy.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
    Optional<Organizador> findByEmailOrganizador(String emailOrganizador);
}
