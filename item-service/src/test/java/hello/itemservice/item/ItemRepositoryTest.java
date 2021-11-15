package hello.itemservice.item;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ItemRepositoryTest {

    Item item1 = new Item("AA", 1000, 100);
    Item item2 = new Item("BB", 2200, 202);


    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
    }


    @Test
    @DisplayName("아이템을 모두 찾는다")
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isGreaterThanOrEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    @DisplayName("아이템의 정보를 업데이트한다")
    public void update() {
    //given
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();
        //when
        Item updateParam = new Item("item777", 777, 777);

        //itemRepository.update(itemId, updateParam);
        savedItem.update(savedItem, updateParam);

        Item findItem = itemRepository.findById(itemId).orElseThrow(AssertionError::new);
        //then
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }


    @Test
    @DisplayName("아이템이 저장된다")
    public void save() {
        //when
        Item savedItem = itemRepository.save(new Item("CC", 3330, 333));


        //then
        Item findItem = itemRepository.findById(savedItem.getId()).orElseThrow(AssertionError::new);
        assertThat(findItem).isEqualTo(savedItem);

    }



    @Test
    @DisplayName("아이템을 id로 찾는다")
    public void findById() {
        //given

        //when

        //then
        throw new AssertionError();
    }


}