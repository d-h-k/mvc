package com.example.SpringBootPlaygound.bean_validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class LauncherValidator {




    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Person>> violations;

        Person person = new Person();




        // 1 - 에러가 나지 않음
        person.setName("64자 이하의 글자는 에러가 나지 않습니다");
        System.out.println(person.toString());
        violations = validator.validate(person);
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println((violation.getMessage()));
        }
        System.out.println(System.lineSeparator() + System.lineSeparator()+ System.lineSeparator() + System.lineSeparator());




        // 2 - 에러 발생
        person.setName("64자 이상의 글자 입력!" +
                "1234567890" + "1234567890" +
                "1234567890" + "1234567890" +
                "1234567890" + "1234567890");
        System.out.println(person.toString());
        violations = validator.validate(person);
        for (ConstraintViolation<Person> violation : violations) {
            System.out.println((violation.getMessage()));
        }


    }

}
