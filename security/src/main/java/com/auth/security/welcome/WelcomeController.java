package com.auth.security.welcome;

import com.auth.security.auth.service.SessionUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    private final HttpSession session;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String reHome (Model model) {
        SessionUser user = (SessionUser)session.getAttribute("user");
        logger.info("model : {}",model.toString());
        if(user != null) {
            logger.info("SessionUser : {}", user.toString());
        }
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String home () {
        return "/home";
    }

    @GetMapping("/test")
    public String sdfs () {
        return "test";
    }

    @GetMapping("/hello")
    public String hello () {
        return "/hello";
    }

    @GetMapping("/login")
    public String login () {
        return "/login";
    }

}
