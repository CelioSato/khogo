package com.khogofinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.khogofinal.domain.Telegram;


@Repository
public interface TelegramRepository extends JpaRepository<Telegram, Integer>{
	
	@Query("SELECT obj FROM Telegram obj WHERE obj.aparelho = :aparelho")
	Optional<Telegram> findDevice(@Param("aparelho") String aparelho);

}
