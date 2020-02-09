import java.rmi.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            int RMIPort;
            String hostName;

            String firstName = null;
            String secondName = null;

            Scanner input = new Scanner(System.in);

            AccMgrIntf h=(AccMgrIntf)Naming.lookup("rmi://localhost:4999"+ 
            "/Server");
            System.out.println("Lookup completed ");

            boolean exit = false;
            boolean loggedIn = false;
            System.out.println("Welcome to RMI e-Banking System");
            while (!exit) {
                System.out.println("1) Create Account");
                System.out.println("2) Log In");
                System.out.println("3) Exit");
                String choice = input.nextLine();
                if (choice.equals("1")) {
                    System.out.println("**************Registration************");
                    System.out.println("Please Enter your First name:");
                    String fName = input.nextLine();
                    System.out.println("Please Enter your Password:");
                    String pass = input.nextLine();
                    System.out.println("**************************************");
                    boolean verdict = h.register(fName, pass);
                    if (verdict) {
                        System.out.println("Account registered successfully!");
                        // Move the user into the log in and use bank account menu
                        choice = "2";
                        // Set the user to be logged in & store their name information
                        loggedIn = true;
                        firstName = fName;
                    } else {
                        System.out.println("User already exists.");
                    }
                }
                // Allow user to skip through log in step by making this an if instead of an else if
                if (choice.equals("2")) {
                    while (!loggedIn) {
                        System.out.println("***************Logging In**************");
                        System.out.println("Login Panel");
                        System.out.println("Please Enter your First Name");
                        String fName = input.nextLine();
                        System.out.println("Please Enter your Password");
                        String pass = input.nextLine();
                        System.out.println("*************************************");

                        // call remote method
                        boolean verdict = h.login(fName, pass);
                        if (verdict) {
                            loggedIn = true;
                            firstName = fName;
                        } else {
                            System.out.println("Login information was not found.");
                        }
                    }

                    // Start menu functionality.
                    System.out.println("Welcome to RMI e-Banking System, " + firstName + ".");
                    String menuChoice = "";
                    while (!menuChoice.equals("5")) {
                        System.out.println("What would you like to do?");
                        System.out.println("1) Check Balance");
                        System.out.println("2) Withdraw");
                        System.out.println("3) Deposit");
                        System.out.println("4) Transfer");
                        System.out.println("5) Exit.");

                        menuChoice = input.nextLine();
                        if (menuChoice.equals("1")) {
                            double balance = h.getBalance(firstName);

                            if (balance != -1) {
                                System.out.println("Your current balance is: $" + balance);
                            } else {
                                System.out.println("");
                            }
                        } else if (menuChoice.equals("2")) {
                            System.out.println("How much would you like to withdraw?");
                            double amount = input.nextDouble();
                            input.nextLine();

                            boolean successful = h.withdraw(amount, firstName);
                            if (successful) {
                                System.out.println("You successfully withdraw $" + amount + " from your account.");
                            } else {
                                System.out.println("Not Enough Balance.");
                            }
                        } else if (menuChoice.equals("3")) {
                            System.out.println("How much would you like to deposit?");
                            double amount = input.nextDouble();
                            input.nextLine();

                            boolean successful = h.deposit(amount, firstName);
                            if (successful) {
                                System.out.println("You successfully deposited $" + amount + " into your account.");
                            } else {
                                System.out.println("");
                            }
                        } else if (menuChoice.equals("4")) {
                            System.out.println("Please enter user's name  whom you want to transfer: ");
                            String payeeName = input.nextLine();
                            
                            System.out.println("How much would you like to transfer?");
                            double amount = input.nextDouble();
                            input.nextLine();
                            
                            boolean transfer = h.transfer(firstName, payeeName, amount);

                            if (transfer) {
                                System.out.println("You successfully transfered $" + amount + " into " + payeeName + "account.");
                            } else {
                                System.out.println("Sorry, this transaction could not be performed. Did you supply the correct password?");
                            }
                        }else if (menuChoice.equals("5")) {
                            exit = true;
                        } else {
                            System.out.println("Please choose a valid option from the menu listed.");
                        }
                        System.out.println("");
                    }
                } else if (choice.equals("3")) {
                    exit = true;
                } else {
                    System.out.println("Please enter a selection from the menu options listed.");
                }
            }
            System.out.println("Thank you.");

        } // end try
        catch (Exception e) {
            System.out.println("Exception in Client: " + e);
        } // end catch
    }

}

