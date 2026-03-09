public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount(String holder, double deposit, double rate) {
        super(holder, deposit);
        this.interestRate = rate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        transactions.add("Interest applied: " + interest);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance)
            throw new InsufficientFundsException("Not enough balance.");

        balance -= amount;
        transactions.add("Withdrawn: " + amount);
    }
}