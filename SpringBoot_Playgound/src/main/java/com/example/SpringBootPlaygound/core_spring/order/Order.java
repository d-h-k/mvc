package com.example.SpringBootPlaygound.core_spring.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Order {

    private Long memberId;
    private String itemName;
    private Integer itemPrice;
    private Integer discountPrice;

    public int calculatePrice() {
        return this.itemPrice - this.discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}

