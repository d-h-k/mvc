package com.auth.security.auth;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

public class AuthController {
    @GetMapping("/users")
    public Map<String, Object> user(@AuthenticationPrincipal Principal principal) {
        return Collections.singletonMap("name", principal.getName());
    }

}
