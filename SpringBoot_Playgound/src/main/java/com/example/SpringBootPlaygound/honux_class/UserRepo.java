package com.example.SpringBootPlaygound.honux_class;

import com.example.SpringBootPlaygound.honux_class.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
    // 데리브드 커리,웨어절 안에 복잡한거 들어가면 잘 안되고
    // 간단한것들은 자동완성 해주니까 걍 쓰셈 2.0버전와서 생각보다 좋아졌다

    // 굳이 jpa랑 비교하면 비효율적인것도 많지먄 걍 써라

}
