package com.auth.security.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryJpaImpl extends JpaRepository<Account,Long> {
    public Account findByEmail(String email);
}
