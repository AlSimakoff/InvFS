package org.example.controller;
import org.example.repositories.*;
import org.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@Controller
public class TextController {

    public String date_now(){
               return java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    Integer Item_Delete_Id;
    String Item_Delete_Place;
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
        Store check=ItemService.getStore(outsideFull.getId()).orElse(new Store(0,"",""));
        if (check.id()!=0) {
            Outside outside = new Outside(outsideFull.getId(),
                    date_now(),
                    outsideFull.getTake(),
                    "Выдача",
                    outsideFull.getNote());
            ItemService.saveOutside(outside);
            ItemService.deleteStore(outside.id());
            Transaction trs = new Transaction(outside.id(), "Склад", "Лично", date_now(),
                    outside.note());
            ItemService.saveTransaction(trs);
            return "redirect:/index";
        }
        else {
            return  "redirect:/index#error";
        }
    }
    @PostMapping(value = "/VerOut")
    public String  verOut( OutsideFull outsideFull, Model model){
        Outside check=ItemService.getOutside(outsideFull.getId()).orElse(new Outside(0,"","",
                                                                               "",""));
        if (check.id()!=0) {
            Store store = new Store(outsideFull.getId(), outsideFull.getAction(), outsideFull.getNote());
            ItemService.saveStore(store);
            ItemService.deleteOutside(outsideFull.getId());
            Transaction trs = new Transaction(outsideFull.getId(), "Лично", "Склад", date_now(),
                                              outsideFull.getNote());
            ItemService.saveTransaction(trs);
            return "redirect:/index";
        }
        else {
            return "redirect:/index#error";
        }
    }
    @PostMapping(value = "/VerOfc")
    public String  verOfc( Office office, Model model){
        Office check=ItemService.getOffice(office.id()).orElse(new Office(0,"","",""));
        if (check.id()!=0) {
        Store store=new Store(office.id(),office.Action(),office.note());
        ItemService.saveStore(store);
        ItemService.deleteOffice(office.id());
        Transaction trs=new Transaction(office.id(),"Офис","Склад", date_now(), office.note() );
        ItemService.saveTransaction(trs);
        return "redirect:/index";
        }
        else {
            return "redirect:/index#error";
        }
    }

    @PostMapping(value = "/VidOfc")
    public String  vidOfc( Office office, Model model){
        Store check=ItemService.getStore(office.id()).orElse(new Store(0,"",""));
        if (check.id()!=0) {
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
        else {
            return "redirect:/index#error";
        }
    }



    @GetMapping(value = "/storedelete")
    public String  StoreDelete( Integer Id,  Model model ){
        Store store=ItemService.getStore(Id).orElse(new Store(0,"",""));
        if (store.id()!=0){
            Item_Delete_Id=Id;
            Item_Delete_Place="store";
           return "redirect:/index#delete";

       }

        return "redirect:/index#error";
    }
    @GetMapping(value = "/officedelete")
    public String  OfficeDelete( Integer Id,  Model model ){
        Office office=ItemService.getOffice(Id).orElse(new Office(0,"","",""));
        if (office.id()!=0){
            Item_Delete_Id=Id;
            Item_Delete_Place="office";
            return "redirect:/index#delete";

        }

        return "redirect:/index#error";
    }
    @GetMapping(value = "/outsidedelete")
    public String  OutsideDelete( Integer Id,  Model model ){
        Outside outside=ItemService.getOutside(Id).orElse(new Outside(0,"","","",""));
        if (outside.id()!=0){
            Item_Delete_Id=Id;
            Item_Delete_Place="outside";
            return "redirect:/index#delete";

        }

        return "redirect:/index#error";
    }
    @PostMapping(value = "/delete")
    public String  Delete( Office office,  Model model ){
        Transaction trs=new Transaction(Item_Delete_Id,Item_Delete_Place,"Списание", date_now(),office.note() );
        ItemService.saveTransaction(trs);
        if (Item_Delete_Place.equals("store")){
            ItemService.deleteStore(Item_Delete_Id);
            return "redirect:/index";
        }
        if (Item_Delete_Place.equals("outside")){
            ItemService.deleteOutside(Item_Delete_Id);
            return "redirect:/index";
        }
        if (Item_Delete_Place.equals("office")){
            ItemService.deleteOffice(Item_Delete_Id);
            return "redirect:/index";
        }
        return "redirect:/index#error";
    }
}


