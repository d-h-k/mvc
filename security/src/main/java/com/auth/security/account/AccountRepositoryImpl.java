package com.auth.security.account;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccountRepositoryImpl implements AccountRepository{

    private static final ConcurrentHashMap<Long, Account> accountMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> emailIndex = new ConcurrentHashMap<>();
    private static Long primaryKey = 1L;

    @Override
    public Account findByEmail(String email) {
        return accountMap.get(emailIndex.get(email));
    }

    @Override
    public Account findById(Long id) {
        return accountMap.get(id);
    }

    @Override
    public synchronized Account save(Account account) {
        if(account.getEmail().isBlank() || account.getEmail().isEmpty() || account.getEmail()==null) {
            throw new AssertionError("email constraint violation");
        }
        accountMap.put(primaryKey,account);
        emailIndex.put(account.getEmail(), primaryKey);
        primaryKey++;
        return account;
    }

}
