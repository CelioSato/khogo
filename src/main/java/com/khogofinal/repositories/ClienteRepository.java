package com.khogofinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khogofinal.domain.Cliente;

@Repository		         								
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	//Selecionar os clientes onde o "obj.usuario.id", da entidade Cliente, for igual ao ID que eu passar na URL
		@Query("SELECT obj FROM Cliente obj where obj.usuario.id = :id")
		List<Cliente> findClienteByUsuarioId(@Param("id") int id);
	
	
	
	
	/*@Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	
	@Query("SELECT obj FROM Cliente obj WHERE obj.device = :device")
	Optional<Cliente> findDevice(@Param("device") String device);
	*/
	


}
