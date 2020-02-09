import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class AccMgr extends UnicastRemoteObject implements AccMgrIntf {

    private List<BankAccount> accounts = new ArrayList<BankAccount>();

    public AccMgr() throws RemoteException {
        super();
    }

    private BankAccount locateAccount(String firstName) {
        for (BankAccount b : accounts) {
            if (b.getFirstName().equals(firstName)) {
                return b;
            }
        }
        return null;
    }

    public boolean register(String firstName, String password) throws RemoteException {
        BankAccount b = new BankAccount(firstName, password);
        if (!accounts.contains(b)) {
            accounts.add(b);
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String firstName, String password) throws RemoteException {
        BankAccount b = locateAccount(firstName);
        if (b != null) {
            b.setLoggedIn(true);
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount, String firstName) throws RemoteException {
        BankAccount b = locateAccount(firstName);

        if (b != null) {
            return b.withdraw(amount);
        } else {
            return false;
        }
    }

    public boolean deposit(double amount, String firstName) throws RemoteException {
        BankAccount b = locateAccount(firstName);

        if (b != null) {
            b.deposit(amount);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean transfer(String firstName, String payeeName, double amount) throws RemoteException {
        BankAccount b = locateAccount(payeeName);
        BankAccount b1 = locateAccount(firstName);
        if (b != null) {
            b.deposit(amount);
            b1.withdraw(amount);
            return true;
        } else {
            return false;
        }
    }


    public double getBalance(String firstName) throws RemoteException {
        BankAccount b = locateAccount(firstName);

        if (b != null) {
            return b.getBalance();
        } else {
            return -1;
        }
    }
}
