package com.auth.security.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


class JasyptConfigTest {

    @Test
    @DisplayName("암호화가 정상적으로 동작한다")
    void jasypt(){
        String url = "jdbc:aws-rds://north-east-3b-rds-exp/rds:8876";
        String username = "myRootMyRDS";
        String password = "root";

        String encryptUrl = jasyptEncrypt(url);
        String encryptUsername = jasyptEncrypt(username);
        String encryptPassword = jasyptEncrypt(password);

        System.out.println("encryptUrl : " + encryptUrl);
        System.out.println("encryptUsername : " + encryptUsername);
        System.out.println("encryptPassword" + encryptPassword);

        assertThat(url).isEqualTo(jasyptDecryt(encryptUrl));
    }

    private String jasyptEncrypt(String input) {
        String key = "password1234!";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private String jasyptDecryt(String input){
        String key = "password1234!";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }

}
