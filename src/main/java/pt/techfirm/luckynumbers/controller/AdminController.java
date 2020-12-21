package pt.techfirm.luckynumbers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.techfirm.luckynumbers.model.SlotMachineModel;
import pt.techfirm.luckynumbers.service.LuckyNumbersService;

@Controller
@RequestMapping("admin")
public class AdminController {

    private LuckyNumbersService luckyNumbersService;

    @GetMapping(value="/profits")
    public String admin(Model model) {
        model.addAttribute("slotMachines", luckyNumbersService.getSlotMachineModelList());
        return "admin";
    }

    @Autowired
    public void setLuckyNumbersService(LuckyNumbersService luckyNumbersService) {
        this.luckyNumbersService = luckyNumbersService;
    }
}