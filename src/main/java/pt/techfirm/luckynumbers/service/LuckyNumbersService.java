package pt.techfirm.luckynumbers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.techfirm.luckynumbers.model.SlotMachineModel;
import pt.techfirm.luckynumbers.repository.SlotMachineRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class LuckyNumbersService {

    @Autowired
    private SlotMachineRepository slotMachineRepository;
    private boolean validation = false;
    private int counter = 0;
    private int income = 0; //CHAPTER 4
    private List<SlotMachineModel> slotMachineModelList = new LinkedList<>(); //CHAPTER 3
    private int moneyLost = 0;
    int moneyEarned = 0;

    public SlotMachineModel getSlotMachine(String machineName) {
        return slotMachineRepository
                .findById(machineName)
                .orElseGet(() -> {
                    SlotMachineModel newSlotMachineModel = new SlotMachineModel();
                    newSlotMachineModel.setMachineName(machineName);
                    return slotMachineRepository.save(newSlotMachineModel);
                });
    }

    public SlotMachineModel insertCoin(String machineName) {
        SlotMachineModel slotMachineModel = slotMachineRepository.findById(machineName).orElseThrow();
        slotMachineModel.insertCoin();
        return slotMachineRepository.save(slotMachineModel);
    }

    public SlotMachineModel play(String machineName) {


        SlotMachineModel slotMachineModel = slotMachineRepository.findById(machineName).orElseThrow();

        int moneyToEarn = slotMachineModel.getCoins();
        int moneyToLose = slotMachineModel.getCoins() * 5;

        Random random = new Random();
        List<Integer> result = new LinkedList<>();
        /*result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);

         */

        result.add(2);
        result.add(2);
        result.add(2);

        slotMachineModel.setResult(result);
        System.out.println(result.toString());
        slotMachineModel.setWon(won(slotMachineModel));

        if (moneyLost >= 25) {


            if (moneyEarned >= moneyLost) {
                moneyLost = 0;
                moneyEarned = 0;
            }

            while (won(slotMachineModel)) {
                System.out.println("WHILE " + won(slotMachineModel));
                System.out.println(result.size());
                System.out.println("1:" + result.toString());
                result.clear();
                System.out.println("2:" + result.toString());
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                System.out.println("3:" + result.toString());
                slotMachineModel.setResult(result);
                System.out.println("\nWHILE " + result.toString());

                slotMachineModel.setWon(won(slotMachineModel));
                slotMachineModel.deposit(slotMachineModel.getCoins());

                if (!won(slotMachineModel)) {
                    break;
                }
            }
            income += moneyToEarn;
            moneyEarned += moneyToEarn;

            System.out.println("MONEY EARNED: " + moneyEarned);
            System.out.println("MONEY LOST: " + moneyLost);
        } else if (slotMachineModel.getWon() && validation == false) {
            System.out.println("GANHOUUUUU");
            System.out.println("COUNTER: " + counter);
            counter = 0;
            validation = true;
            income -= moneyToLose; //CHAPTER 4
            moneyLost += moneyToLose;
            System.out.println("Income: " + income); //CHAPTER 4
            System.out.println(validation);
            slotMachineModel.withdraw(slotMachineModel.getCoins());

        } else if (validation && slotMachineModel.getWon() && counter < 5) {
            System.out.println("VALIDAÇÂO");
            counter++;
            income += moneyToEarn; //CHAPTER 4
            System.out.println("Income: " + income); //CHAPTER 4
            System.out.println("COUNTER: " + counter);

            if (counter == 5) {
                validation = false;
            }

            while (won(slotMachineModel)) {
                System.out.println("WHILE " + won(slotMachineModel));
                System.out.println(result.size());
                System.out.println("1:" + result.toString());
                result.clear();
                System.out.println("2:" + result.toString());
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                System.out.println("3:" + result.toString());
                slotMachineModel.setResult(result);
                System.out.println("\nWHILE " + result.toString());

                slotMachineModel.setWon(won(slotMachineModel));
                slotMachineModel.deposit(slotMachineModel.getCoins());

                if (!won(slotMachineModel)) {
                    break;
                }
            }


        } else if (!validation && counter == 10) {
            System.out.println("Entrou: " + counter);

            counter = 0;
            income -= moneyToLose; //CHAPTER 4
            moneyLost += moneyToLose;


            while (!won(slotMachineModel)) {
                System.out.println("WHILE " + won(slotMachineModel));
                System.out.println(result.size());
                System.out.println("1:" + result.toString());
                result.clear();
                System.out.println("2:" + result.toString());
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                System.out.println("3:" + result.toString());
                slotMachineModel.setResult(result);
                System.out.println("\nWHILE " + result.toString());

                slotMachineModel.setWon(won(slotMachineModel));
                slotMachineModel.deposit(slotMachineModel.getCoins());

                if (won(slotMachineModel)) {
                    break;
                }
            }
            System.out.println("Income: " + income); //CHAPTER 4


        } else {
            counter++;
            income += moneyToEarn; //CHAPTER 4
            System.out.println("Income: " + income); //CHAPTER 4
            System.out.println("COUNTER: " + counter);
            System.out.println("ALOOOOOOOO");
            System.out.println(validation);
            slotMachineModel.deposit(slotMachineModel.getCoins());
        }

        slotMachineModel.setCoins(0);

        slotMachineModel.setIncome(income);

        return slotMachineRepository.save(slotMachineModel);
    }

    private boolean won(SlotMachineModel slotMachineModel) {
        if (slotMachineModel.getResult() == null) return false;
        if (slotMachineModel.getResult().isEmpty() || slotMachineModel.getResult().size() < 3) return false;
        return slotMachineModel.getResult().get(0) == slotMachineModel.getResult().get(1)
                && slotMachineModel.getResult().get(0) == slotMachineModel.getResult().get(2);
    }

    public Integer getIncome() {
        return income;
    }

    public List<SlotMachineModel> getSlotMachineModelList() {
        return (List<SlotMachineModel>) slotMachineRepository.findAll();
    }

    public void setSlotMachineModelList(List<SlotMachineModel> slotMachineModelList) {
        this.slotMachineModelList = slotMachineModelList;
    }
}
