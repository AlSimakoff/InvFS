package org.example.controller;
import org.example.repositories.Office;
import org.example.repositories.Outside;
import org.example.repositories.Store;
import org.example.repositories.item;
import org.example.services.OfficeFull;
import org.example.services.itemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class TextController {

    @Autowired
    private itemServiceImpl ItemService;
    @GetMapping("/index")
    public String showItems(Model model) {
        List<item> Item = ItemService.getAllItems();
        /*List<Office> office = ItemService.getAllOffice();*/
        OfficeFull office=ItemService.getOfficeFull(1);
        List<Store> store = ItemService.getAllStore();
        List<Outside> outside = ItemService.getAllOutside();
        model.addAttribute("item", Item);
        model.addAttribute("office", office);
        model.addAttribute("store", store);
        model.addAttribute("outside", outside);

        return "index";
    }
}

