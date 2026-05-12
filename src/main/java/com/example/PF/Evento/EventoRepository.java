package com.example.PF.Evento;

import com.example.PF.User.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {


    boolean existsByNome(String nome);
    // SELECT * From curso WHERE dataCriacao >= ?data
    void deleteByNome(String nome);

    Optional<Evento> findByNome(String nome);



}
