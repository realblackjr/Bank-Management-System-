public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(String holder, double deposit, double overdraftLimit) {
        super(holder, deposit);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {

        if (balance + overdraftLimit < amount)
            throw new InsufficientFundsException("Overdraft limit exceeded.");

        balance -= amount;
        transactions.add("Withdrawn: " + amount);
    }
}