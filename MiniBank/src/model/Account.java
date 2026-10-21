package model;

import model.annotation.Id;
import java.io.Serializable;

public class Account implements Serializable {
    @Id
    private String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public synchronized void deposit(long amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }
}
