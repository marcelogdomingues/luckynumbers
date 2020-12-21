package pt.techfirm.luckynumbers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.techfirm.luckynumbers.model.SlotMachineModel;
import pt.techfirm.luckynumbers.service.MultipleLuckyNumbersService;


@Controller
@RequestMapping("multipleluckynumbers")
public class MultipleLuckyNumberController {

    @Autowired
    private MultipleLuckyNumbersService multipleLuckyNumbersService;

    @GetMapping()
    public String multipleLuckyNumbers(Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.getSlotMachine("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "MultipleLuckyNumbers";
    }

    @GetMapping(value = "/{id}")
    public String machineId(@PathVariable String id, Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.getSlotMachine(id);
        model.addAttribute("slotMachine", slotMachine);
        return "MultipleLuckyNumbers";
    }

    @GetMapping("insert-coin")
    public String insertCoin(Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.insertCoin("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "MultipleLuckyNumbers :: #controls";
    }

    @GetMapping("/{id}/multipleluckynumbers/insert-coin")
    public String insertCoinWithId(@PathVariable String id, Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.insertCoin(id);
        model.addAttribute("slotMachine", slotMachine);
        return "MultipleLuckyNumbers :: #controls";
    }

    @GetMapping("/play")
    public String play(Model model) {
        System.out.println("ENTROU");
        SlotMachineModel slotMachine = multipleLuckyNumbersService.multipleplay("hackathon");
        model.addAttribute("slotMachine", slotMachine);
        return "MultipleLuckyNumbers :: #slot-machine";
    }

    @GetMapping("/{id}/multipleluckynumbers/play")
    public String playWithId(@PathVariable String id, Model model) {
        SlotMachineModel slotMachine = multipleLuckyNumbersService.multipleplay(id);
        model.addAttribute("slotMachine", slotMachine);
        return "MultipleLuckyNumbers :: #slot-machine";
    }

}
