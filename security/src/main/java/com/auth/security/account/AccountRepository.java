package com.auth.security.account;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {

    public Account findByEmail(String email);

    public Account findById(Long id);

    public Account save(Account account);
}
