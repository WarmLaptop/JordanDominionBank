package JordanDominionBank;

import JordanDominionBank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jordan on 7/19/2017.
 */
public class User {
    private String name;
    private String id;
    private String address;
    private int phoneNumber;
    private String username;
    private String password;

    private ArrayList<Account> accounts;

    public User(String name, String address, int phoneNumber) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.address = address;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {

        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return String.valueOf(this.phoneNumber).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
    }

    public List getAccounts() {
        return accounts;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

}
