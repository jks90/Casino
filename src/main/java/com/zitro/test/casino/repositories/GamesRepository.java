package com.zitro.test.casino.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zitro.test.casino.models.Games;

@Repository
public interface GamesRepository extends JpaRepository<Games, UUID>  {
	
	Optional<Games> findByName(String name);

}
