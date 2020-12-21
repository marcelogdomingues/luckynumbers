package pt.techfirm.luckynumbers.model;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "slotmachine")
public class SlotMachineModel {

    @Id
    private String machineName;
    private Integer coins;
    private Integer stash;
    private Boolean won;
    private Integer income;

    @ElementCollection
    @CollectionTable(name = "results", joinColumns = @JoinColumn(name = "machineName"))
    private List<Integer> result;

    public SlotMachineModel() {
        this.coins = 0;
        this.stash = 0;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineName() {
        return machineName;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    public void insertCoin() {
        this.coins++;
    }

    public List<Integer> getResult() {
        return result;
    }

    public void setResult(List<Integer> result) {
        this.result = result;
    }

    public Integer getStash() {
        return stash;
    }

    public void setStash(Integer stash) {
        this.stash = stash;
    }

    public void deposit(Integer coins) {
        this.stash += coins;
    }

    public void withdraw(Integer coins) {
        this.stash -= coins;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

}
