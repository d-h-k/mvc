package hello.itemservice.domain;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ItemRepositoryTest {

    static {
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

    }

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
    }


    @Test
    @DisplayName("아이템이 저장된다")
    public void save() {
        //given

        //when

        //then
        throw new AssertionError();

    }


    @Test
    @DisplayName("아이템 전체를 조회한다")
    public void findAll() {
        //given

        //when

        //then
        throw new AssertionError();

    }


    @Test
    @DisplayName("아이템을 id로 찾는다")
    public void findById() {
        //given

        //when

        //then
        throw new AssertionError();
    }


    @Test
    @DisplayName("아이템의 정보를 업데이트한다")
    public void update() {
        //given

        //when

        //then
        throw new AssertionError();

    }

}