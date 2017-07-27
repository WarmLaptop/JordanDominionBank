package JordanDominionBank.util;

import JordanDominionBank.User;
import JordanDominionBank.model.Account;
import JordanDominionBank.model.ChequingAccount;
import JordanDominionBank.model.SavingsAccount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by Jordan on 7/19/2017.
 */
public class Database {
    private static String username;
    private static String password;
    private static User user;

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static User getUser() {
        return user;
    }

    public static void load() throws IOException {

        user = new User("Jordan","1234 Yonge St",1234);
        Path path = Paths.get("C:\\Users\\Jordan\\Desktop\\NuPhysics\\Final Project\\src\\JordanDominionBank\\util\\UsernameAndPassword.txt");
        BufferedReader br = Files.newBufferedReader(path);

        String data = br.readLine();
        String[] dataSeparated = data.split(",");
        username = dataSeparated[0];
        password = dataSeparated[1];
        br.close();

        Path path2 = Paths.get("C:\\Users\\Jordan\\Desktop\\NuPhysics\\Final Project\\src\\JordanDominionBank\\util\\AccountsInformation.txt");
        BufferedReader br2 = Files.newBufferedReader(path2);

        while ((data = br2.readLine()) != null) {
            String[] dataSplitted = data.split(",");
            if (dataSplitted[0].equals("saving")) {
                SavingsAccount saving = new SavingsAccount();
                user.getAccounts().add(saving);
                saving.deposit(Integer.parseInt(dataSplitted[1]));
            } else if (dataSplitted[0].equals("chequing")) {
                ChequingAccount chequing = new ChequingAccount();
                user.getAccounts().add(chequing);
                chequing.deposit(Integer.parseInt(dataSplitted[1]));
            }}
        br2.close();

    }

    public static void save() throws IOException {
        Path path = Paths.get("JordanDominionBank\\util\\AccountsInformation.txt");
        System.out.println(path);
        BufferedWriter bw = Files.newBufferedWriter(path);

        Iterator<Account> iterator = user.getAccounts().iterator();

        while (iterator.hasNext()) {
            Account account = iterator.next();
            if (account.getClass().isInstance(new SavingsAccount())) {
                bw.write(String.format("%s,%s", "saving", account.getBalance()));
            } else if (account.getClass().isInstance(new ChequingAccount())) {
                bw.write(String.format("%s,%s", "chequing", account.getBalance()));
            }
            bw.newLine();
        }
        bw.close();


    }
}
