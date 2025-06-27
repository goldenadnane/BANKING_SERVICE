package org.skypy;

import org.skypy.classes.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse("10/01/2012");
        Date date2 = dateFormat.parse("13/01/2012");
        Date date3 = dateFormat.parse("14/01/2012");


        Account account = new Account();
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);

        List<Account.Transaction> transactions = account.getTransactions();

        transactions.get(0).setDate(date1);
        transactions.get(1).setDate(date2);
        transactions.get(2).setDate(date3);

        account.printStatement();


        try {
            account.withdraw(3000);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
            account.deposit(-200);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
            account.withdraw(-600);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}