/*
 * Ian Borges
 * COP 2210 Cristy Charters
 * Nov 23, 2023
 * Program Functionality:
 * A cash register that allows the user to input what they wish to order at the Starbucks location. Furthermore,
 * all of the user's choices will be outputted to a salesTransactions.txt file.
 * This program utilizes for each loops to constantly calculate costs and other variables, and uses validation loops/switch statements/
 * if statements for constant validation checks of the user's requests.
 */
package finalchallenge;

import Food.Snack;
import Food.Drink;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CashRegister {

    public Scanner keyboard = new Scanner(System.in);
    public SalesTransaction aTransaction;
    public StarbucksStore aStore;

    public static void main(String args[]) throws IOException {

        int menuOption = 0;

        CashRegister myCashRegister = new CashRegister();
        myCashRegister.openStore();

        System.out.println("Welcome to my Starbucks Store!  We are now open for business.");

        do {
            menuOption = myCashRegister.displayMenu();
            switch (menuOption) {
                case 0:
                    myCashRegister.openTab();
                    break;
                case 1:
                    myCashRegister.processDrink();
                    break;
                case 2:
                    myCashRegister.processSnack();
                    break;
                case 3:
                    myCashRegister.processSale();
                    break;
                case 4:
                    myCashRegister.processMerch();
                    break;
                case 5:
                    myCashRegister.summarizeStoreSales();
                    break;
                default:
                    System.out.println("Invalid menu selection; Choose 1 - 4 or 5 to Close Store");

            }


        } while (menuOption != 5);



    }


    public int displayMenu()
    {
        System.out.println("\n\nWhat would you like to do next? ");
        if (aTransaction == null)
        {
            System.out.println("\t0. Open a tab for a customer.");
            System.out.println("\t5. Close store for the day.");
        }
        else
        {
            System.out.println("\t1. Sell a drink.");
            System.out.println("\t2. Sell a snack.");
            System.out.println("\t3. Close a sale.");
            System.out.println("\t4. Sell Merchandise");

        }

        int menuOption = keyboard.nextInt();
        keyboard.nextLine();

        if (aTransaction == null && menuOption != 0 && menuOption !=5)
        {
            menuOption = 0;   //force menu option to be 0
        }
        return menuOption;
    }



    public void openStore() {
        final int storeCode = 703;
        final String storeLocation = "FIU";
        final int hourOpen = 0700;
        final int hourClosed = 2200;
        ArrayList<SalesTransaction> dailySales = new ArrayList<SalesTransaction>();
        aStore = new StarbucksStore(storeCode, storeLocation, hourOpen, hourClosed, dailySales);
    }




    public void openTab()
    {
        System.out.println("\n\nWelcome to Starbucks! \nWhat is your name please?");
        String custName = keyboard.nextLine();
        Date date;
        date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss-a");
        String timeStamp = dateFormat.format(date);
        aTransaction = new SalesTransaction(timeStamp, custName);


    }

    public void processDrink()
    {
        Drink aDrink;

        System.out.println("What is the name of your drink?");
        String drinkName = keyboard.nextLine();

        System.out.println("What is the size of your drink? (small, medium, large)");
        String drinkSize = keyboard.nextLine();

        double drinkOunces = 0;
        double drinkCost = 0;
        if (drinkSize.equalsIgnoreCase("small"))
        {
            drinkOunces = 8.0;
            drinkCost = 2.50;
        }else if (drinkSize.equalsIgnoreCase("medium"))
        {
            drinkOunces = 12.5;
            drinkCost = 3.75;
        }
        else if (drinkSize.equalsIgnoreCase("large"))
        {
            drinkOunces = 16.8;
            drinkCost = 5.90;
        }
        else
        {
            drinkOunces = 4.0;
            drinkCost = 1.25;
        }


        System.out.println("Do you want to add cream?");
        String creamChoice = keyboard.nextLine();
        boolean addedCream = false;
        if (creamChoice.equalsIgnoreCase("yes"))
        {
            addedCream = true;
            drinkCost += 1.50;
        }

        System.out.println("Do you want to add a flavor?");
        String flavorChoice = keyboard.nextLine();
        String addedFlavor = null;
        if (flavorChoice.equalsIgnoreCase("yes"))
        {
            System.out.println("What is your desired flavor?");
            addedFlavor = keyboard.nextLine();
            drinkCost += 1.95;
        }


        System.out.println("Your drink cost is " + drinkCost);
        aDrink = new Drink(drinkName, drinkSize, drinkOunces, addedCream, addedFlavor, drinkCost);
        aTransaction.getDrinks().add(aDrink);
        System.out.println(aTransaction);



    }

    public void processMerch() {
        Merchandise aMerch;
        System.out.println("What is the name of your merch?");
        String merchName = keyboard.nextLine();
        System.out.println("Whats the size of " + merchName + " ?");
        String merchSize = keyboard.nextLine().toLowerCase();
        int quantity;
        double cost = 0.0;

        if (merchName.equalsIgnoreCase("coffee cup") || merchName.equalsIgnoreCase("coffee carafe")) {
            switch (merchSize) {
                case "large":
                    cost = 8.50;
                    break;
                case "medium":
                    cost = 6.50;
                    break;
                case "small":
                    cost = 4.50;
                    break;
                default:
                    cost = 4.00;
                    break;
            }
        } else if (merchName.equalsIgnoreCase("insulated bag") || merchName.equalsIgnoreCase("coffee tumbler")) {
            switch (merchSize) {
                case "large":
                    cost = 12.50;
                    break;
                case "medium":
                    cost = 10.50;
                    break;
                case "small":
                    cost = 8.50;
                    break;
                default:
                    cost = 8.00;
                    break;
            }
        } else {
            switch (merchSize) {
                case "large":
                    cost = 8.50;
                    break;
                case "medium":
                    cost = 6.50;
                    break;
                case "small":
                    cost = 4.50;
                    break;
                default:
                    cost = 4.25;
                    break;
            }
        }


        System.out.println("How many " + merchName + " will you buy?");
        quantity = keyboard.nextInt();
        keyboard.nextLine();

        aMerch = new Merchandise(merchName, merchSize, cost, quantity);
        aTransaction.getMerch().add(aMerch);
        System.out.println(aTransaction);

    }

    public void processSnack()
    {
        Snack aSnack;
        System.out.println("What is the name of your snack?");
        String snackName = keyboard.nextLine();
        String description;
        double cost = 0.0;
        if (snackName.equalsIgnoreCase("apple") || snackName.equalsIgnoreCase("banana"))
        {
             description = "fruit snack";
             cost = 2.50;

        }
        else if (snackName.equalsIgnoreCase("bagel") || snackName.equalsIgnoreCase("cake") ||
                 snackName.equalsIgnoreCase("cookies") )
        {
            description = "carbohydrate snack";
            cost = 3.50;
        }
        else
        {
            description = "other snack";
            cost = 1.50;
        }

        aSnack = new Snack(snackName, description, cost);
        aTransaction.getSnacks().add(aSnack);
        System.out.println(aTransaction);

    }



    
    public void processSale() throws IOException
    {
           String payType;
           DecimalFormat df = new DecimalFormat("##0.00");
           double grandTotal = aTransaction.tabulateSale();
           System.out.println("Please pay $" + df.format(grandTotal));

           do {
               System.out.println("Will you be paying by Credit Card or Cash?");
               System.out.println("Enter 1 for Credit Card, 2 for Cash");
               payType = keyboard.nextLine();

               if (!payType.equalsIgnoreCase("1") && !payType.equalsIgnoreCase("2")) {
                   System.out.println("You have not entered a number from 1-2.");
               }
           } while (!payType.equalsIgnoreCase("1") && !payType.equalsIgnoreCase("2"));



           if (payType.contains("1"))
           {
               System.out.println("What credit card will you be using?");
               String creditCardName = keyboard.nextLine();
               System.out.println("What is your cc number?");
               String creditCardNumber = keyboard.nextLine();
               aTransaction.setCustomerPaymentMethod("Credit Card");
               aTransaction.setCreditCardName(creditCardName);
               aTransaction.setCreditCardNumber(creditCardNumber);
           }
           else
           {
               System.out.println("Please pay in cash now.");
               aTransaction.setCustomerPaymentMethod("Cash");
           }
           aTransaction.setAmountPaid(grandTotal);
           System.out.println("Thank you for stopping by.  Please come back soon!");

           recordTransaction();

    }


    public void recordTransaction()  throws IOException
    {
        FileWriter fw = new FileWriter("salesTransactions.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(aTransaction);
        pw.close();

        aStore.getDailySales().add(aTransaction);
        aTransaction = null; //reset after sale is complete, to force menu to show open a new tab.


    }


    public void summarizeStoreSales() throws IOException
    {

        double dailyGrandTotal = 0;

        dailyGrandTotal = aStore.accumulateDailySales();

        DecimalFormat df = new DecimalFormat("##00.00");
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss-a" +
                "");
        String timeStamp = dateFormat.format(date);

        String storeMessage = "On " + timeStamp + ", the " + aStore.getLocation() + " Starbucks store has sold $" + df.format(dailyGrandTotal);
        System.out.println(storeMessage);
        
        FileWriter fw = new FileWriter("salesTransactions.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(storeMessage);
        pw.close();
    }

}
