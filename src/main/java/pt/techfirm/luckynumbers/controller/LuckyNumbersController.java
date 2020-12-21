package pt.techfirm.luckynumbers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.techfirm.luckynumbers.model.SlotMachineModel;
import pt.techfirm.luckynumbers.service.LuckyNumbersService;

import java.util.Random;

@Controller
@RequestMapping("luckynumbers")
public class LuckyNumbersController {

    @Autowired
    private LuckyNumbersService luckyNumbersService;

    @GetMapping()
    public String luckyNumbers(Model model) {
        SlotMachineModel slotMachine = luckyNumbersService.getSlotMachine("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "luckyNumbers";
    }


    @GetMapping(value = "/{id}")
    public String machineId(@PathVariable String id, Model model) {
        SlotMachineModel slotMachine = luckyNumbersService.getSlotMachine(id);
        model.addAttribute("slotMachine", slotMachine);
        return "luckyNumbers";
    }

    @GetMapping("insert-coin")
    public String insertCoin(Model model) {
        SlotMachineModel slotMachine = luckyNumbersService.insertCoin("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "luckyNumbers :: #controls";
    }
    @GetMapping("/{id}/luckynumbers/insert-coin")
    public String insertCoinWithId(@PathVariable String id, Model model) {
        SlotMachineModel slotMachine = luckyNumbersService.insertCoin(id);
        model.addAttribute("slotMachine", slotMachine);
        return "luckyNumbers :: #controls";
    }

    @GetMapping("/{id}/luckynumbers/play")
    public String playWithId(@PathVariable String id, Model model) {
        SlotMachineModel slotMachine = luckyNumbersService.play(id);
        model.addAttribute("slotMachine", slotMachine);
        return "luckyNumbers :: #slot-machine";
    }

    @GetMapping("play")
    public String play(Model model) {
        SlotMachineModel slotMachine = luckyNumbersService.play("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "luckyNumbers :: #slot-machine";
    }

}