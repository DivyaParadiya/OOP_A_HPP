package util;

import model.Account;
import java.io.*;

public class StatePersister {
    public static void save(Account[] accounts, String file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(accounts);
        out.close();
    }

    public static Account[] load(String file) throws IOException, ClassNotFoundException {
        File f = new File(file);
        if (!f.exists()) {
            return new Account[0];
        }
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Account[] accounts = (Account[]) in.readObject();
        in.close();
        return accounts;
    }
}
