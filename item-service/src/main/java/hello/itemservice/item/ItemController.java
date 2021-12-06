package hello.itemservice.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute( "items",items);
        return "basic/items";
    }


    @PostConstruct
    public void sample() {
        itemRepository.save(new Item("AA", 1000,100));
        itemRepository.save(new Item("BB", 2200,202));
        itemRepository.save(new Item("CC", 3330,333));


    }

}
