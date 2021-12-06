package com.auth.security.auth.service;

import com.auth.security.account.Account;
import lombok.ToString;

import java.io.Serializable;

@ToString
//꼭 직렬화 해야되기 때문에 -> 직렬화 마커 인터페이스잖아, 이게 스프링 시큐리티 표준이람 ㅠㅠ
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
        this.picture = account.getPicture();

    }
}
