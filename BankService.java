import java.util.*;

public class BankService {

    private Map<String, Account> accounts = new HashMap<>();
    private List<String> globalLog = new ArrayList<>();

    public SavingsAccount createSavingsAccount(String name, double deposit, double rate) {

        SavingsAccount acc = new SavingsAccount(name, deposit, rate);
        accounts.put(acc.getAccountNumber(), acc);

        globalLog.add("Created Savings Account: " + acc.getAccountNumber());
        return acc;
    }

    public CurrentAccount createCurrentAccount(String name, double deposit, double overdraft) {

        CurrentAccount acc = new CurrentAccount(name, deposit, overdraft);
        accounts.put(acc.getAccountNumber(), acc);

        globalLog.add("Created Current Account: " + acc.getAccountNumber());
        return acc;
    }

    public Account getAccount(String accNo) {

        Account acc = accounts.get(accNo);

        if (acc == null)
            throw new NoSuchElementException("Account not found.");

        return acc;
    }

    public void deposit(String accNo, double amount) {

        Account acc = getAccount(accNo);
        acc.deposit(amount);

        globalLog.add("Deposit " + amount + " to " + accNo);
    }

    public void withdraw(String accNo, double amount) throws InsufficientFundsException {

        Account acc = getAccount(accNo);
        acc.withdraw(amount);

        globalLog.add("Withdraw " + amount + " from " + accNo);
    }

    public void transfer(String from, String to, double amount) throws InsufficientFundsException {

        Account source = getAccount(from);
        Account dest = getAccount(to);

        source.withdraw(amount);
        dest.deposit(amount);

        globalLog.add("Transfer " + amount + " from " + from + " to " + to);
    }

    public void displayAllAccounts() {

        System.out.println("\n--- ALL ACCOUNTS ---");

        for (Account acc : accounts.values())
            System.out.println(acc + "\n");
    }

    public void displayTransactionHistory(String accNo) {

        Account acc = getAccount(accNo);

        System.out.println("\n--- TRANSACTIONS ---");

        for (String t : acc.getTransactions())
            System.out.println(t);
    }

    public void displayGlobalLog() {

        System.out.println("\n--- GLOBAL LOG ---");

        for (String log : globalLog)
            System.out.println(log);
    }
}