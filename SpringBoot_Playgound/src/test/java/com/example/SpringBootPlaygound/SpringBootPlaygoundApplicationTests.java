package com.example.SpringBootPlaygound;

import com.example.SpringBootPlaygound.honux_class.Food;
import com.example.SpringBootPlaygound.honux_class.Github;
import com.example.SpringBootPlaygound.honux_class.User;
import com.example.SpringBootPlaygound.honux_class.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional // 저장하지않고 트랜잭숀으로 자동백업해서 테스트 여려번 돌릴수있게(디비는 저장하는거자나)
@SpringBootTest // 이거 써야 스프링으로 테스트 할수 있고 오토와이어드 쓸 수 있다
@Disabled
class SpringBootPlaygoundApplicationTests {


    private Logger logger = LoggerFactory.getLogger(SpringBootPlaygoundApplicationTests.class);
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private UserRepo userRepo;

    @Test
    @DisplayName("1) IoC 컨테이너 정상동작 확인")
    void contextLoads() {
        assertThat(logger).isNotNull();
        assertThat(ctx).isNotNull();
    }

    @Test
    @DisplayName("2) Spring Data JDBC MYSQL 연동 확인")
    void readUser() {
		Long id = 1L;
		User user = userRepo.findById(id).get();
		assertThat(user).isNotNull();
		logger.info("User ID : {} : {}",id,user);

		Long id2 = 77L;
		assertThat(userRepo.findById(id2).isPresent()).isFalse();

		Iterable<User> users = userRepo.findAll();
		//assertThat(((Collection) user).size()).isEqualTo(2);
        //이거뭐하는거지 왜 에러나는거지 ->> 이거 그냥 몇개들어있는지 확인하는거임
        // 이게 콜렉션인지 확인하는거 필요해
        // 이게 풀스캔이라는건데 안좋은거야

        assertThat(userRepo.count()).isEqualTo(3);//기본으로 3개 넣어줬으니까 3개 있는지 확인하는거야
        //이건 풀스캔이 아니야 프라이머리키로 돌리는거야 삐트리의 인덱스를 검색하는거
        //디비는 메타데이터가 없어


        //푸드부분
        Set<Food> foodSet = user.getFoods();
        //assertThat(foodSet.size()).isPositive();

    }


    @Test
    @DisplayName("3) DerivedQuery 를 사용해서 데이터를 가져옴 : 이름으로 이메일을 쿼리하고, 이메일로 이름을 조회하는 테스트")
    void readUserNameAndEmail() {
        User user = new User("test@test.com", "tester",
                new Github("tester","https://www.photo.com"));
        String wrongName = "devilCry";
        String wrongEmail = "holy@moly.net";

        userRepo.save(user);

        User emailUser = userRepo.findByEmail(user.getEmail()).get();
        assertThat(emailUser.getName()).isEqualTo(user.getName());

        User nameUser = userRepo.findByName(user.getName()).get();
        assertThat(nameUser.getEmail()).isEqualTo(user.getEmail());

        assertThat(userRepo.findByName(wrongName).isPresent()).isFalse();
        assertThat(userRepo.findByEmail(wrongEmail).isPresent()).isFalse();

    }

    @Test
    @DisplayName("4)새로운 유저 생성이 되는지 확인")
    void create() {
        User user = new User("test@zz.com", "hello", new Github("zzz", "https://qqqqq.com") );

        user = userRepo.save(user);
        assertThat(user.getId()).isGreaterThanOrEqualTo(2);
        logger.info("Create new user : {}",user);
        
        
    }
    
    @Test
    @DisplayName("5) 유저 정보 업데이트")
    void updateUser() {
        User user =  new User("josh","river@finix.gokor",
                new Github("josh","www.josh.com"));
        String updateName = "MacbookPro16";
        String updateEmail = "new_M1X";
        userRepo.save(user);

        //name update check
        User updateUser = userRepo.findByName(user.getName()).get();
        updateUser.setName(updateName);
        userRepo.save(updateUser);
        User step1User = userRepo.findByName(updateName).get();
        assertThat(step1User.getName()).isEqualTo(updateName);


        //Email update check
        updateUser = userRepo.findByEmail(user.getEmail()).get();
        updateUser.setEmail(updateEmail);
        userRepo.save(updateUser);
        User step2User = userRepo.findByEmail(updateEmail).get();
        assertThat(step2User.getEmail()).isEqualTo(updateEmail);
    }


    @Test
    @DisplayName("6) 유저 정보 삭제")
    void delete() {
        Long id1 = 1L;
        Long id2 = 2L;
        Long id3 = 3L;

        User user = userRepo.findById(id1).get();
        userRepo.delete(user);

        userRepo.deleteById(id2);
        userRepo.deleteById(id3);
        assertThat(userRepo.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("7) 푸드를 추가하자")
    void addFoods() {
        User user = userRepo.findById(1L).get();
        user.addMultiFood(
                new Food("Kingcrab",747),
                new Food("iceNoodle",787),
                new Food("arboMuran",210),
                new Food("tuna",380)
                );
        userRepo.save(user);

        Set<Food> foods = userRepo.findById(user.getId()).get().getFoods();
        assertThat(foods.size()).isGreaterThanOrEqualTo(4);
        for(Food f:foods) {
            System.out.println(f);
        }
    }

}
