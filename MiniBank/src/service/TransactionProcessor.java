package service;

import model.Account;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionProcessor {
    private ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void addAll(Iterable<Account> list) {
        for (Account account : list) {
            add(account);
        }
    }

    public void deposit(String number, long amount) {
        Account account = accounts.get(number);
        if (account != null) account.deposit(amount);
    }

    public void processBatch(long amount) throws InterruptedException {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (Account account : accounts.values()) {
                    account.deposit(amount);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
