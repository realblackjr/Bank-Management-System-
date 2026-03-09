import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    protected String accountNumber;
    protected String accountHolder;
    protected double balance;
    protected List<String> transactions = new ArrayList<>();

    private static int counter = 1001;

    public Account(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.accountNumber = "ACC" + counter++;
        transactions.add("Account created with balance: " + initialDeposit);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount);
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException;

    public List<String> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               "\nHolder: " + accountHolder +
               "\nBalance: " + balance + " RWF";
    }
}