package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping/user")
public class MappingClassController {

    @GetMapping
    public String users() {
        return "GET user";
    }

    @PostMapping
    public String addUser() {
        return "POST user";
    }

    @GetMapping("/userId")
    public String findUser(@PathVariable String userId) {
        return "GET userId=" + userId;
    }

    @PatchMapping("/userId")
    public String updateUser(@PathVariable String userId) {
        return "POST userId=" + userId;
    }

    @DeleteMapping("/userId")
    private String deleteUser(@PathVariable String userId) {
        return "DELETE userId=" + userId;
    }

}
