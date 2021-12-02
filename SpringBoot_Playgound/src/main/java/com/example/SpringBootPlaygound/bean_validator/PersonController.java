package com.example.SpringBootPlaygound.bean_validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping(value = "valid")
    public ResponseEntity<?> hiruYoMan(@Valid @RequestBody Person person) {
        logger.info("리퀘스트 들어옴 : {}", person.toString());
        return ResponseEntity.ok().build();
    }

}
