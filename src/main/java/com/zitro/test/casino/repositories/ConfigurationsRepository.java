package com.zitro.test.casino.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zitro.test.casino.models.Configurations;

@Repository
public interface ConfigurationsRepository extends JpaRepository<Configurations, UUID>  {
	
	Optional<List<Configurations>> findByGameTypeId(UUID id);

}
