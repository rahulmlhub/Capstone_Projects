package com.metlife.repository;

import com.metlife.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
   Optional<UserCredential> findByUserName(String username);
    boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
