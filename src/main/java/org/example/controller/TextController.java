package org.example.controller;
import org.example.repositories.*;
import org.example.services.*;
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

    @GetMapping("/transaction")
    public String showTransaction(Model model) {
        List<TransactionFull> transaction=ItemService.getAllTransactionFull();

        //Add data on web-form
        model.addAttribute("transaction", transaction);

        return "transaction";
    }
    @PostMapping(value = "/AddItem")
    public String addItem(@RequestParam Map<String, String> Map, Model model) {
        item Item=new item(1,Map.get("name"),Map.get("ser"), Map.get("inv"));
        int ItemId=ItemService.saveitem_getid(Item);
        Store store=new Store(ItemId,"Новое","");
        ItemService.saveStore(store);
        Transaction trs=new Transaction(ItemId,"","Склад", date_now(),"" );
        ItemService.saveTransaction(trs);
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
        Transaction trs=new Transaction(outside.id(),"Склад","Лично", date_now(),outside.note() );
        ItemService.saveTransaction(trs);
        return "redirect:/index";
    }
    @PostMapping(value = "/VerOut")
    public String  verOut( OutsideFull outsideFull, Model model){
        Store store=new Store(outsideFull.getId(),outsideFull.getAction(),outsideFull.getNote());
        ItemService.saveStore(store);
        ItemService.deleteOutside(outsideFull.getId());
        Transaction trs=new Transaction(outsideFull.getId(),"Лично","Склад", date_now(),outsideFull.getNote());
        ItemService.saveTransaction(trs);
        return "redirect:/index";
    }
    @PostMapping(value = "/VerOfc")
    public String  verOfc( Office office, Model model){
        Store store=new Store(office.id(),office.Action(),office.note());
        ItemService.saveStore(store);
        ItemService.deleteOffice(office.id());
        Transaction trs=new Transaction(office.id(),"Офис","Склад", date_now(), office.note() );
        ItemService.saveTransaction(trs);
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
        Transaction trs=new Transaction(office.id(),"Склад","Офис", date_now(),office.note() );
        ItemService.saveTransaction(trs);
        return "redirect:/index";
    }


}

