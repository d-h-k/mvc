package com.auth.security.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
//명시적으로 필드선언을 했을 때 타입에 선언된 부분에 (onlyExplicitlyIncluded = true)를 선언해야 적용
//callSuper = true로 설정하면 부모 클래스 필드 값들도 동일한지 체크하며, callSuper = false로 설정(기본값)하면 자신 클래스의 필드 값들만 고려
// 어디팅에 생성시간까지 고려할 필요 없어서 false
public class Account /*BaseEntityAuditing*/ {

    private Long id;
    private String name;
    private String email;
    private String picture;
    private String password;
    private AccountRole role;

    @Builder
    public Account(String name, String email, String picture, AccountRole role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public Account update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

/*

 */
