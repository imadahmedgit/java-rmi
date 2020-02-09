import java.rmi.*;

public interface AccMgrIntf extends Remote {

    public boolean register(String firstName, String password) throws RemoteException;

    public boolean login(String firstName, String password) throws RemoteException;

    public boolean withdraw(double amount, String firstName) throws RemoteException;

    public boolean deposit(double amount, String firstName) throws RemoteException;

    public double getBalance(String firstName) throws RemoteException;
    
    public boolean transfer(String firstName1, String firstName2, double amount) throws RemoteException;
}
