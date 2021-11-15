package hello.itemservice.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
}
