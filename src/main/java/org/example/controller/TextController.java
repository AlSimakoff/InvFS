package org.example.controller;
import org.example.repositories.item;
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
    @GetMapping("/")
    public String showItems(Model model) {
        List<item> Item = ItemService.getAllItems();
        model.addAttribute("item", Item);
        return "index";
    }
}

