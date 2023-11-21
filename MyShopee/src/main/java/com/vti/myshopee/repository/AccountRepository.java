package com.vti.myshopee.repository;

import com.vti.myshopee.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByUsername(String username);
    Optional<Account> findByUsername(String username);
}
