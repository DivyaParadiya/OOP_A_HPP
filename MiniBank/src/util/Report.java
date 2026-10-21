package util;

import java.io.*;

public class Report {
    public static String make(String file) throws IOException {
        long deposits = 0;
        long withdrawals = 0;
        File f = new File(file);
        if (!f.exists()) {
            return "Deposits: 0\nWithdrawals: 0";
        }
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split(" ");
            if (p.length == 2) {
                long amount = Long.parseLong(p[1]);
                if (p[0].equals("DEPOSIT")) deposits += amount;
                if (p[0].equals("WITHDRAW")) withdrawals += amount;
            }
        }
        br.close();
        return "Deposits: " + deposits + "\nWithdrawals: " + withdrawals;
    }

    public static void add(String file, String type, long amount) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(type + " " + amount + "\n");
        writer.close();
    }
}
