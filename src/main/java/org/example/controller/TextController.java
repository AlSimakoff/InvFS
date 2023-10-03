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

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TextController {

    public String date_now(){
        String datenw=java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
               return datenw;
    }
    @Autowired
    private itemServiceImpl ItemService;
    @GetMapping("/index")
    public String showItems(Model model) {
        List<item> Item = ItemService.getAllItems();
        List<OfficeFull> office=ItemService.getAllOfficeFull();
        List<StoreFull> store = ItemService.getAllStoreFull();
        List<OutsideFull> outside = ItemService.getAllOutsideFull();
        //Add data on web-form
        model.addAttribute("item", Item);
        model.addAttribute("office", office);
        model.addAttribute("store", store);
        model.addAttribute("outside", outside);
        // objects for forms on buttons
        OutsideFull outside_for_form=new OutsideFull(0,"","","",date_now(),date_now(),"","");
        OfficeFull office_for_form=new OfficeFull(0,"","","","","","");
        item item_for_form=new item(0,"","","");
        model.addAttribute("outsideFull", outside_for_form);
        model.addAttribute("officeForm", office_for_form);
        model.addAttribute("ItemForm", item_for_form);
        return "index";
    }
    @PostMapping(value = "/AddItem")
    public String addItem(@RequestParam Map<String, String> Map, Model model) {
        item Item=new item(1,Map.get("name"),Map.get("ser"), Map.get("inv"));
        ItemService.saveitem(Item);
        Store store=new Store(Item.id(),"Новое","");
        ItemService.saveStore(store);

        return "redirect:/index";
    }
    @PostMapping(value = "/VidOut")
    public String  saveOut( OutsideFull outsideFull, Model model){

        Outside outside=new Outside(outsideFull.getId(),
                date_now(),
                outsideFull.getTake(),
                "Выдача",
                outsideFull.getNote());
        ItemService.saveOutside(outside);
        ItemService.deleteStore(outside.id());
        return "redirect:/index";
    }
    @PostMapping(value = "/VerOut")
    public String  verOut( OutsideFull outsideFull, Model model){
        Store store=new Store(outsideFull.getId(),outsideFull.getAction(),outsideFull.getNote());
        ItemService.saveStore(store);
        ItemService.deleteOutside(outsideFull.getId());
        return "redirect:/index";
    }
    @PostMapping(value = "/VerOfc")
    public String  verOfc( Office office, Model model){
        Store store=new Store(office.id(),office.Action(),office.note());
        ItemService.saveStore(store);
        ItemService.deleteOffice(office.id());
        return "redirect:/index";
    }

    @PostMapping(value = "/VidOfc")
    public String  vidOfc( Office office, Model model){
        Office officeBd=new Office(office.id(),
                date_now(),
                "Выдача",
                office.note());
        ItemService.saveOffice(officeBd);
        ItemService.deleteStore(office.id());

        return "redirect:/index";
    }


}

