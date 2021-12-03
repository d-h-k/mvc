package com.auth.security.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountRole {
    ADMIN("ROLE_ADMIN","ADMIN"),
    USER("ROLE_USER","USER"),
    GUEST("ROLE_GUEST","GUEST");
    private final String Key;
    private final String title;
}
