package hello.itemservice.item;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    void update(Item savedItem, Item updateParam) {
        savedItem.setItemName(updateParam.getItemName());
        savedItem.setQuantity(updateParam.quantity);
        savedItem.setPrice(updateParam.price);
    }
}
