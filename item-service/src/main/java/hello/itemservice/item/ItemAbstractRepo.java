package hello.itemservice.item;

import java.util.List;

public interface ItemAbstractRepo {
    Item save(Item item);
    Item findById(Long id);
    List<Item> findAll();
    void update(Long id, ItemDto itemDto );
    // 중복이냐 명확성이냐 >> 항상 명확성
}
