package pt.techfirm.luckynumbers.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.techfirm.luckynumbers.model.SlotMachineModel;
import pt.techfirm.luckynumbers.repository.SlotMachineRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


@Service
public class MultipleLuckyNumbersService {
    @Autowired
    private SlotMachineRepository slotMachineRepository;
    private Boolean wonValidation = false;
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

    public SlotMachineModel multipleplay(String machineName) {

        SlotMachineModel slotMachineModel = slotMachineRepository.findById(machineName).orElseThrow();

        Random random = new Random();
        List<Integer> result = new LinkedList<>();

        int moneyToEarn = slotMachineModel.getCoins();
        int moneyToLose = slotMachineModel.getCoins() * 5;

        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);

        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);

        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);
        result.add(random.nextInt(9) + 1);


        /*result.add(2);
        result.add(2);
        result.add(2);

         */
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
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
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
        } else if (slotMachineModel.getWon() && validation == false /*BRUNO*/) {
            System.out.println("GANHOUUUUU");
            System.out.println("COUNTER: " + counter);
            counter = 0;
            validation = true;
            income -= moneyToLose; //CHAPTER 4
            moneyLost += moneyToLose;
            System.out.println(validation);
            slotMachineModel.withdraw(slotMachineModel.getCoins());

        } else if (validation && slotMachineModel.getWon() && counter < 5) { //BRUNO
            System.out.println("VALIDAÇÂO");
            counter++;
            income += moneyToEarn; //CHAPTER 4
            System.out.println("COUNTER: " + counter);

            if (counter == 5) {
                validation = false;
            }

            while (won(slotMachineModel)) { //BRUNO

                System.out.println("WHILE " + won(slotMachineModel));
                System.out.println(result.size());
                System.out.println("1:" + result.toString());
                result.clear();
                System.out.println("2:" + result.toString());
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);


                System.out.println("3:" + result.toString());
                //slotMachineModel.setResult(result);
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

            while (!won(slotMachineModel)) { //BRUNO
                System.out.println("WHILE " + won(slotMachineModel));
                System.out.println(result.size());
                System.out.println("1:" + result.toString());
                result.clear();
                System.out.println("2:" + result.toString());
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
                result.add(random.nextInt(9) + 1);
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

        } else {
            counter++;
            income += moneyToEarn; //CHAPTER 4
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

        if (slotMachineModel.getResult() == null) return wonValidation;
        if (slotMachineModel.getResult().isEmpty() || slotMachineModel.getResult().size() < 3) return wonValidation;

        if (slotMachineModel.getResult().get(0) == slotMachineModel.getResult().get(1) && slotMachineModel.getResult().get(1) == slotMachineModel.getResult().get(2)) { //HORIZONTAL
            System.out.println("WIN");
            wonValidation = true;

        } else if (slotMachineModel.getResult().get(3) == slotMachineModel.getResult().get(4) && slotMachineModel.getResult().get(4) == slotMachineModel.getResult().get(5)) { //HORIZONTAL
            System.out.println("WIN");
            wonValidation = true;


        } else if (slotMachineModel.getResult().get(6) == slotMachineModel.getResult().get(7) && slotMachineModel.getResult().get(7) == slotMachineModel.getResult().get(8)) { //HORIZONTAL
            System.out.println("WIN");
            wonValidation = true;


        } else if (slotMachineModel.getResult().get(0) == slotMachineModel.getResult().get(4) && slotMachineModel.getResult().get(4) == slotMachineModel.getResult().get(8)) { //DIAGONAL
            System.out.println("WIN");
            wonValidation = true;


        } else if (slotMachineModel.getResult().get(2) == slotMachineModel.getResult().get(4) && slotMachineModel.getResult().get(4) == slotMachineModel.getResult().get(6)) { //DIAGONAL
            System.out.println("WIN");
            wonValidation = true;

        } else if (slotMachineModel.getResult().get(0) == slotMachineModel.getResult().get(3) && slotMachineModel.getResult().get(3) == slotMachineModel.getResult().get(6)) { //VERTICAL
            System.out.println("WIN");
            wonValidation = true;

        } else if (slotMachineModel.getResult().get(1) == slotMachineModel.getResult().get(4) && slotMachineModel.getResult().get(4) == slotMachineModel.getResult().get(7)) { //VERTICAL
            System.out.println("WIN");
            wonValidation = true;

        } else if (slotMachineModel.getResult().get(2) == slotMachineModel.getResult().get(5) && slotMachineModel.getResult().get(5) == slotMachineModel.getResult().get(8)) { //VERTICAL
            System.out.println("WIN");
            wonValidation = true;

        } else {
            wonValidation = false;
        }


        return wonValidation;
    }


}
