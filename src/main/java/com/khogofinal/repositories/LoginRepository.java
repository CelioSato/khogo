package com.khogofinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khogofinal.domain.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{
	
	Optional<Login> findByUsername(String username);

	boolean existsByUsername(String username);

}
