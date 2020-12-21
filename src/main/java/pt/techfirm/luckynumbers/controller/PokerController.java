package pt.techfirm.luckynumbers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.techfirm.luckynumbers.model.SlotMachineModel;
import pt.techfirm.luckynumbers.service.MultipleLuckyNumbersService;

@Controller
@RequestMapping("poker")
public class PokerController {

    @Autowired
    private MultipleLuckyNumbersService multipleLuckyNumbersService;

    @GetMapping()
    public String poker(Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.getSlotMachine("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "poker";
    }

    @GetMapping("insert-coin")
    public String insertCoin(Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.insertCoin("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "Poker :: #controls";
    }

    @GetMapping("/play")
    public String play(Model model) {
        System.out.println("ENTROU");
        SlotMachineModel slotMachine = multipleLuckyNumbersService.multipleplay("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "Poker :: #slot-machine";
    }

}

