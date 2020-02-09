import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server {

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String portNum, registryURL;

        try {
            AccMgr exportedObj = new AccMgr();
			LocateRegistry.createRegistry(4999); 
			  
            
            Naming.rebind("rmi://localhost:4999"+ 
                          "/Server",exportedObj);
			System.out.println("Server is ready"); 
			
        }// end try
        catch (Exception re) {
            System.out.println("Exception in Server.main: " + re);
        } // end catch
    } // end main

    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();  // This call will throw an exception
        } catch (RemoteException e) {
            System.out.println("RMI registry cannot be located at port " + RMIPortNum);
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
            System.out.println("RMI registry created at port " + RMIPortNum);
        }
    } // end startRegistry

    private static void listRegistry(String registryURL) throws RemoteException, MalformedURLException {
        System.out.println("Registry " + registryURL + " contains: ");
        String[] names = Naming.list(registryURL);
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    } //end listRegistry

} // end class
