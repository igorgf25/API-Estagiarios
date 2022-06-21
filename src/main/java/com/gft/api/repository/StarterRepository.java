package com.gft.api.repository;

import com.gft.api.model.Starter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StarterRepository extends JpaRepository<Starter, String> {
    public List<Starter> findAllByOrderByNomeAsc();
    public List<Starter> findAllByOrderByNomeDesc();
    public List<Starter> findAllByNomeStartsWithOrderByNomeAsc(@Param("nome") String nome);
}
