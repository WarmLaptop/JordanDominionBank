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
        //Balance is instantiated with new money class. Money is zero for all accounts.
        balance = new Money();
        id = rand.nextInt(900000) + 100000;
    }
    //Getter for balance.
    public String getBalance() {
        return balance.getBalance();
    }
    //Two cases where dollar and dollar, cent value exists for the method variable.
    public void deposit(int dollar, int cent) {
        this.balance.addMoney(dollar, cent);
    }
    public void deposit(int dollar) {
        this.balance.addMoney(dollar, 0);
    }
    //Same for withdraw. Note that it returns a boolean value. True if subtracted money is smaller than the balance.
    public boolean withdraw(int dollar, int cent) {
        return this.balance.subtractMoney(dollar, cent);
    }
    public boolean withdraw(int dollar) {
        return this.balance.subtractMoney(dollar, 0);
    }
    //Getter for id.
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

    //Inner class of Money.
    private class Money {
        private int dollar;
        private int cent;

        //Initialize balance as zero when no parameters are existing.
        public Money() {
            this(0, 0);
        }
        //Returns dollar value when only one parameter exists.
        public Money(int dollar) {
            this(dollar, 0);
        }
        //Returns dollar AND cent value when two parameter exists.
        public Money(int dollar, int cent) {
            addMoney(dollar, cent);

        }
        //Getter for the current balance (this.dollar)
        public String getBalance() {
            return this.dollar+"";
        }
        //Method for adding money.
        public void addMoney(int dollar, int cent) {
            int totalCent = this.cent + cent;
            this.dollar = dollar + (totalCent / 100);
            this.cent = totalCent % 100;
        }
        //Method for subtracting money.
        public boolean subtractMoney(int dollar, int cent) {
            if (dollar > this.dollar || (dollar == this.dollar && cent > this.cent)) {
                //Error if the method variable amount is larger than the this.dollar amount.
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


    } //End of Money inner class.



}
