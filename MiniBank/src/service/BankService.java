package service;

import model.Account;
import repository.Repository;
import exception.*;
import java.util.ArrayList;

public class BankService {
    private Repository<Account> repository;
    private TransactionProcessor processor;

    public BankService(Repository<Account> repository, TransactionProcessor processor) {
        this.repository = repository;
        this.processor = processor;
    }

    public void openAccount(String number, String name, long balance) throws BankException {
        if (!Validator.validAccountNumber(number) || !Validator.validName(name)) {
            throw new BankException("Invalid account details");
        }
        if (balance < 0) {
            throw new InvalidAmountException("Invalid balance");
        }
        if (repository.findById(number) != null) {
            throw new BankException("Account already exists");
        }
        Account account = new Account(number, name, balance);
        repository.save(account);
        processor.add(account);
    }

    public void deposit(String number, long amount) throws BankException {
        Account account = find(number);
        if (!Validator.validAmount(amount)) {
            throw new InvalidAmountException("Amount must be positive");
        }
        account.deposit(amount);
    }

    public void withdraw(String number, long amount) throws BankException {
        Account account = find(number);
        if (!Validator.validAmount(amount)) {
            throw new InvalidAmountException("Amount must be positive");
        }
        if (!account.withdraw(amount)) {
            throw new InsufficientFundsException("Insufficient balance");
        }
    }

    public void transfer(String from, String to, long amount) throws BankException {
        Account a = find(from);
        Account b = find(to);
        if (!Validator.validAmount(amount)) {
            throw new InvalidAmountException("Amount must be positive");
        }
        synchronized (a) {
            synchronized (b) {
                if (!a.withdraw(amount)) {
                    throw new InsufficientFundsException("Insufficient balance");
                }
                b.deposit(amount);
            }
        }
    }

    public String statement(String number) throws BankException {
        Account account = find(number);
        return "Account Number: " + account.getAccountNumber() + "\nOwner: "
                + account.getOwnerName() + "\nBalance: " + account.getBalance();
    }

    public ArrayList<Account> listAccounts() {
        return repository.findAll();
    }

    private Account find(String number) throws AccountNotFoundException {
        Account account = repository.findById(number);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return account;
    }
}
