package com.zitro.test.casino.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zitro.test.casino.models.UsersProviders;

@Repository
public interface UsersProvidersRepository extends JpaRepository<UsersProviders, UUID> {

}
