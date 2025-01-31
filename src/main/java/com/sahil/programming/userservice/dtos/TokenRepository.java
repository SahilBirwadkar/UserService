package com.sahil.programming.userservice.dtos;

import com.sahil.programming.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByValue(String tokenValue);
}
