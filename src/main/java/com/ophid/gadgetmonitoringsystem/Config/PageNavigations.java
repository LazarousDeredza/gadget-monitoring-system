package com.ophid.gadgetmonitoringsystem.Config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageNavigations {

    @GetMapping("addGadgetPage")
    private String addGadgetPage(){
        return "new_gadget";
    }
    @GetMapping("addEmployeePage")
    private String addEmployeePage(){
        return "new_employee";
    }

    @GetMapping("gadgetsPage")
    private String viewGadgets(){
        return "gadgets";
    }

    @GetMapping("assignGadget")
    private  String assignGadget(){
        return "assign_gadget";
    }


    @GetMapping("addLocationPage")
    private String addLocationPage(){
        return "new_location";
    }

    @GetMapping("viewGadget")
    private String viewGadget(){
        return "view_gadget";
    }

    @GetMapping("AddMaintancePage")
    private String addMaintancePage(){
        return "new_maintanance";
    }

    //home
    @GetMapping({"home","/", "", "index"})
    private String home(){
        return "index";
    }


}
