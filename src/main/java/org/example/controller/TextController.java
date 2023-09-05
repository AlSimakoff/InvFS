package org.example.controller;
import org.example.repositories.Office;
import org.example.repositories.Outside;
import org.example.repositories.Store;
import org.example.repositories.item;
import org.example.services.OfficeFull;
import org.example.services.OutsideFull;
import org.example.services.StoreFull;
import org.example.services.itemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TextController {

    @Autowired
    private itemServiceImpl ItemService;
    @GetMapping("/index")
    public String showItems(Model model) {
        List<item> Item = ItemService.getAllItems();
        /*List<Office> office = ItemService.getAllOffice();*/
        List<OfficeFull> office=ItemService.getAllOfficeFull();
        List<StoreFull> store = ItemService.getAllStoreFull();
        List<OutsideFull> outside = ItemService.getAllOutsideFull();
        model.addAttribute("item", Item);
        model.addAttribute("office", office);
        model.addAttribute("store", store);
        model.addAttribute("outside", outside);
        model.addAttribute("outsideFull", outside.get(1));
        model.addAttribute("officeForm", office.get(1));
        model.addAttribute("ItemForm", Item.get(1));
        return "index";
    }
    @PostMapping(value = "/AddItem")
    public String addItem(@RequestParam Map<String, String> Map, Model model) {
        model.addAttribute("111", Map);
        /*ItemService.saveitem(Item);*/

        return "redirect:/index";
    }
    @PostMapping(value = "/VidOut")
    public String  saveOut( OutsideFull outsideFull, Model model){
        model.addAttribute("outside", outsideFull);

        return "redirect:/index";
    }
    @PostMapping(value = "/VerOut")
    public String  verOut( OutsideFull outsideFull, Model model){
        model.addAttribute("outside", outsideFull);

        return "redirect:/index";
    }
    @PostMapping(value = "/VerOfc")
    public String  verOfc( Office office, Model model){

        return "redirect:/index";
    }

    @PostMapping(value = "/VidOfc")
    public String  vidOfc( Office office, Model model){
        model.addAttribute("outside", office);

        return "redirect:/index";
    }


}

