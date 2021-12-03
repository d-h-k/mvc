package com.auth.security.account;

public interface AccountRepository {

    public Account findByEmail(String email);

    public Account findById(Long id);

    public Account save(Account account);
}
