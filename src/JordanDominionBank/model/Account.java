package JordanDominionBank.model;

import java.util.Random;

/**
 * Created by Jordan on 7/19/2017.
 */
public class Account {

    private Money balance;
    private int id;

    Account(){
        Random rand = new Random();
        balance = new Money();
        id = rand.nextInt(900000) + 100000;
    }

    public String getBalance() {
        return balance.getBalance();
    }

    public void deposit(int dollar, int cent) {
        this.balance.addMoney(dollar, cent);
    }

    public void deposit(int dollar) {
        this.balance.addMoney(dollar, 0);
    }

    public boolean withdraw(int dollar, int cent) {
        return this.balance.subtractMoney(dollar, cent);
    }

    public boolean withdraw(int dollar) {
        return this.balance.subtractMoney(dollar, 0);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        if(this.getClass().isInstance(new SavingsAccount())){
            return "saving-"+getId();
        }
        else if(this.getClass().isInstance(new ChequingAccount())){
            return "chequing-"+getId();
        }
        return "" + getId();

    }

    private class Money {
        private int dollar;
        private int cent;

        public Money() {
            this(0, 0);
        }

        public Money(int dollar) {
            this(dollar, 0);
        }

        public Money(int dollar, int cent) {
            addMoney(dollar, cent);

        }

        public String getBalance() {
            return this.dollar+"";
        }

        public void addMoney(int dollar, int cent) {
            int totalCent = this.cent + cent;
            this.dollar = dollar + (totalCent / 100);
            this.cent = totalCent % 100;
        }

        public boolean subtractMoney(int dollar, int cent) {
            if (dollar > this.dollar || (dollar == this.dollar && cent > this.cent)) {
                System.out.println("Error: Cant take more money than what there is");
                return false;
            } else {
                if (cent > this.cent) {
                    this.dollar--;
                    this.cent = (this.cent + 100) - cent;
                    this.dollar -= dollar;
                } else {
                    this.cent -= cent;
                    this.dollar -= dollar;
                }
                return true;
            }
        }
    }
}
