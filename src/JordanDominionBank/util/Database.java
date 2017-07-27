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
    //private access modifier encapsulates the variables so that only the methods can access the variables.
    private static String username;
    private static String password;
    private static User user;
    //Getters and setters for user credentials.
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
        //Instantiate new user.
        user = new User("Jordan","1234 Yonge St",1234);

        //NEED RELATIVE PATH FOR GET METHOD
        Path path = Paths.get("C:\\Users\\Jordan\\Desktop\\NuPhysics\\Final Project\\src\\JordanDominionBank\\util\\UsernameAndPassword.txt");

        //BufferedReader object is instantiated. One of the most common way to read a txt file. The Username and Password txt file is opened.
        BufferedReader br = Files.newBufferedReader(path);
        //The first line is read.
        String data = br.readLine();
        //Split the line of data with a comma.
        String[] dataSeparated = data.split(",");
        username = dataSeparated[0];
        password = dataSeparated[1];
        br.close(); //Close the buffered reader.

        //NEED RELATIVE PATH FOR GET METHOD
        Path path2 = Paths.get("C:\\Users\\Jordan\\Desktop\\NuPhysics\\Final Project\\src\\JordanDominionBank\\util\\AccountsInformation.txt");

        //Opens a second buffered reader object for the Accounts Information txt file. (FORMAT: saving,100)
        BufferedReader br2 = Files.newBufferedReader(path2);
        //Cycles through the lines to be splitted and be added in its respective account types.
        while ((data = br2.readLine()) != null) {
            String[] dataSplitted = data.split(",");
            if (dataSplitted[0].equals("saving")) {
                SavingsAccount saving = new SavingsAccount();
                user.getAccounts().add(saving);
                //deposits the parsed string value into the savings account.
                saving.deposit(Integer.parseInt(dataSplitted[1]));
            } else if (dataSplitted[0].equals("chequing")) {
                ChequingAccount chequing = new ChequingAccount();
                user.getAccounts().add(chequing);
                //deposits the parsed string value into the dchequing account.
                chequing.deposit(Integer.parseInt(dataSplitted[1]));
            }}
        br2.close();//Closes the buffered reader.

    }


    public static void save() throws IOException {
        //Similar structure to load() method. The database is saved.
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
