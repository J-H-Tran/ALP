package academy.learnprogramming;

import java.util.Objects;

public class BankAccount {

    private static final String INVALID_VALUE_MESSAGE = "Invalid Value Entered.";
    private long accountNumber;
    private double balance;
    private String customerName;
    private String email;

    public void deposit(double amount) {

        if(amount < 0) {
            System.out.println(INVALID_VALUE_MESSAGE);
        }

        this.balance += amount;
        System.out.println("Depositing " + amount + " into your account... \n" +
                "You have " + String.format("%.2f", this.balance) + " remaining in your account.\n");
    }

    public void withdraw(double amount) {

        if(amount < 0) {
            System.out.println(INVALID_VALUE_MESSAGE);
        }

        if((this.balance - amount) < 0) {
            System.out.println("Attempting to withdraw " + amount + " from your account... \n" +
                    "Insufficient funds, please make more money you pleb :^) \n" +
                    "you only have " + this.balance + " in your account \n" +
                    "Please withdraw a different amount.");
        } else {
            this.balance -= amount;
            System.out.println("Withdrawing " + amount + " from our account... \n" +
                    "You have " + String.format("%.2f", this.balance) + " remaining in your account.\n");
        }

    }

    public BankAccount() {
        this("Jonathan", "j.tran@gmail.com");    // will set default parameters to constructors, must be first line in default constructor
        System.out.println("Default constructor called.\n");
    }

    public BankAccount(String customerName, String email) {
        this(23451845, 3000, customerName, email);
        System.out.println("Single arg constructor called.\n");
    }

    //Major constructor, where other constructors call this one to set other fields
    public BankAccount(long accountNumber, double balance, String customerName, String email) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        System.out.println("Constructor called.\n");
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountNumber == that.accountNumber &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance, customerName, email);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
