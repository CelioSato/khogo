package com.khogofinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khogofinal.domain.Location;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Location, Integer>{


}
