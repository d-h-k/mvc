package com.auth.security.auth.service;

import com.auth.security.account.Account;
import com.auth.security.account.AccountRepositoryJpaImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final AccountRepositoryJpaImpl accountRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 제네리파이 제니릭화 todo
        OAuth2UserService delegateService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegateService
                .loadUser(userRequest);
        String registrationId = userRequest // 로그인 진행중인 서비스를 구분하는코드,(깃헙,네이버,구글..)
                .getClientRegistration()
                .getRegistrationId();
        String userNameAttributeName = userRequest
                .getClientRegistration() //OAuth2 로그인 진행에서 키가 되는 필드데이터(=PrimaryKey)
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName(); //구글의 기본 코드는 "Sub" 이며, 네이버 등과 동시에 쓰기위해 필요

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Account account = saveOrUpdate(attributes); //@todo 여기서 문제가 생기네

        httpSession.setAttribute("account", new SessionUser(account)); // 왜 SessionUser 따로 정의하는지 저기에

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(account.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Account saveOrUpdate(OAuthAttributes attributes) {
        Account account = accountRepository.findByEmail(attributes.getEmail());
        //if (attributes.getName() != account.getName()) {
            //account.setName(attributes.getName());
        //}
        //if (attributes.getPicture() != account.getPicture()) {
            //account.setPicture(attributes.getPicture());
        //}
        return accountRepository.save(account);
    }
}
