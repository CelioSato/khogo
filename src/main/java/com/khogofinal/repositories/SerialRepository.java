package com.khogofinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khogofinal.domain.SerialNumber;

@Repository
public interface SerialRepository extends JpaRepository<SerialNumber, Integer>{
	
	@Query("SELECT obj FROM SerialNumber obj WHERE obj.serialNumber = :serialNumber")
	Optional<SerialNumber> findDevice(@Param("serialNumber") String serialNumber);
	
}
