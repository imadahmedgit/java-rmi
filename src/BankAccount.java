import java.util.Objects;

class BankAccount {

    private String firstName;
    private String password;
    private double balance;

    private boolean loggedIn = false;

    public BankAccount(String firstName, String password) {
        this.firstName = firstName;
        this.password = password;
        balance = 0;
    }

    public BankAccount(String firstName, String password, double balance) {
        this.firstName = firstName;
        this.password = password;
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.firstName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BankAccount other = (BankAccount) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" + "Firstname=" + firstName + ", password=" + password + ", balance=" + balance + '}';
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean withdraw(double amount) {
        if (balance - amount >= 0) {
            balance = balance - amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }
}
