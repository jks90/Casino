package com.zitro.test.casino.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zitro.test.casino.models.BetTrace;

@Repository
public interface BetTraceRepository extends JpaRepository<BetTrace, UUID> {

}
