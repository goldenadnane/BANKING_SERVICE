package org.skypy.classes;

import org.skypy.service.AccountService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account implements AccountService {

    private int balance = 0;

    private final List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(new Date(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance -= amount;
        transactions.add(new Transaction(new Date(), -amount, balance));
    }


    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }


    public static class Transaction {
        private Date date;
        private final int amount;
        private final int balance;

        public Transaction(Date date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }


        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return String.format("%s || %d   || %d", formatter.format(date), amount, balance);
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
