package com.khogofinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khogofinal.domain.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>{
	
	@Query("SELECT obj FROM Veiculo obj WHERE obj.aparelho = :aparelho")
	Optional<Veiculo> findDevice(@Param("aparelho") String aparelho);
	
	
	//Selecionar os veiculos onde o "obj.cliente.id", da entidade Veiculo, for igual ao ID que eu passar na URL
	@Query("SELECT obj FROM Veiculo obj where obj.cliente.id = :id")
	List<Veiculo> findVeiculoById(@Param("id") int id);
	

}
