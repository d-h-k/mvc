package hello.itemservice.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository; //final 키워드를 빼면 안된다!, 그러면 ItemRepository 의존관계 주입이 안됨

    @PostConstruct
    public void sample() {
        itemRepository.save(new Item("AA", 1000,100));
        itemRepository.save(new Item("BB", 2200,202));
        itemRepository.save(new Item("CC", 3330,333));
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @PostMapping("/add/v1")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price, //why primitive
                            @RequestParam Integer quantity,
                            Model model) {
        Item item = Item
                .builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }


    @PostMapping("/add/v2")
    public String addItemV2(@ModelAttribute(value = "item") Item item,
                            Model model) {
        itemRepository.save(item);
        //model.addAttribute("item", item); 자동으로 추가됨 생략가능
        return "basic/item";
    }

    @PostMapping("/add/v3")
    public String addItemV3(@ModelAttribute Item item, //모델 저장 이름 생략
                            Model model) {
        itemRepository.save(item);
        //(value = "item")도 묵시적으로 클래스이름 들어가게 생략가능
        // 이때 모델에 저장되는 이름은 클래스명 기준이며, 클래스이름의 첫글자만 소문자로 변경되서 저장된다
        return "basic/item";
    }


    @todo : 이거 수정하고 인강들으면 됨 1208(수)
    @GetMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId,
                            Model model) {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        model.addAttribute("item", item);
        return "basic/addForm";
    }



}
