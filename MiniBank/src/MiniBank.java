import model.Account;
import repository.Repository;
import service.BankService;
import service.TransactionProcessor;
import util.StatePersister;
import util.Report;
import exception.BankException;
import java.util.ArrayList;
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();
        TransactionProcessor processor = new TransactionProcessor();
        BankService bank = new BankService(repository, processor);
        Scanner sc = new Scanner(System.in);

        try {
            Account[] oldAccounts = StatePersister.load("accounts.dat");
            repository.saveAll(oldAccounts);
            processor.addAll(repository.findAll());
        } catch (Exception e) {
            System.out.println("No old data found");
        }

        while (true) {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Report");
            System.out.println("8. Batch Deposit");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            try {
                if (choice.equals("1")) {
                    System.out.print("Account number: ");
                    String no = sc.nextLine();
                    System.out.print("Owner name: ");
                    String name = sc.nextLine();
                    System.out.print("Initial balance: ");
                    long amount = Long.parseLong(sc.nextLine());
                    bank.openAccount(no, name, amount);
                    System.out.println("Account created");
                } else if (choice.equals("2")) {
                    System.out.print("Account number: ");
                    String no = sc.nextLine();
                    System.out.print("Amount: ");
                    long amount = Long.parseLong(sc.nextLine());
                    bank.deposit(no, amount);
                    Report.add("transactions.txt", "DEPOSIT", amount);
                    System.out.println("Deposit done");
                } else if (choice.equals("3")) {
                    System.out.print("Account number: ");
                    String no = sc.nextLine();
                    System.out.print("Amount: ");
                    long amount = Long.parseLong(sc.nextLine());
                    bank.withdraw(no, amount);
                    Report.add("transactions.txt", "WITHDRAW", amount);
                    System.out.println("Withdrawal done");
                } else if (choice.equals("4")) {
                    System.out.print("From account: ");
                    String from = sc.nextLine();
                    System.out.print("To account: ");
                    String to = sc.nextLine();
                    System.out.print("Amount: ");
                    long amount = Long.parseLong(sc.nextLine());
                    bank.transfer(from, to, amount);
                    Report.add("transactions.txt", "WITHDRAW", amount);
                    Report.add("transactions.txt", "DEPOSIT", amount);
                    System.out.println("Transfer done");
                } else if (choice.equals("5")) {
                    System.out.print("Account number: ");
                    System.out.println(bank.statement(sc.nextLine()));
                } else if (choice.equals("6")) {
                    ArrayList<Account> accounts = bank.listAccounts();
                    for (Account account : accounts) {
                        System.out.println(account);
                    }
                } else if (choice.equals("7")) {
                    System.out.println(Report.make("transactions.txt"));
                } else if (choice.equals("8")) {
                    processor.processBatch(100);
                    System.out.println("Batch processed");
                } else if (choice.equals("9")) {
                    ArrayList<Account> accounts = bank.listAccounts();
                    StatePersister.save(accounts.toArray(new Account[0]), "accounts.dat");
                    System.out.println("Data saved");
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (BankException | NumberFormatException | java.io.IOException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
