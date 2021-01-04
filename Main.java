import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

class Main {
  // Method for tax calculation
  static double TaxCalc (double SubTotal) {
    double TotalCost = SubTotal * 1.13;
    return TotalCost;
  }

  public static void main(String[] args) throws IOException{
    //Declare & Create File
    File orderFile = new File ("orderinfo.txt");
    orderFile.createNewFile();

    Scanner input = new Scanner(System.in);
    // Array & Variables
    int itemIndex = 0;
    String FirLasName;
    double TotalCost; // This is used to hold the value of the tax calculation
    double SubTotal = 0.0; // before tax calculation

    int [] quantity = new int [5];
    double [] prices = new double [5];
    String [] items = new String [5];

    String [][] itemsPurchased = new String[5][4];

    String [] item = {"MacBook Air", "PS5", "iPhone 12", "Xbox Series X", "RTX 3090"};
    double [] price = {1299.99, 499.99, 979.99, 599.99, 1999.99};

    // Menu screen
    System.out.println("Welcome to WorstBuy!");
    System.out.println("Please enter your first and last name.");
    FirLasName = input.nextLine();
    String [] name = FirLasName.split("\\s");

    System.out.println("\nHere are the items in stock: ");
    for (int i=0; i<5; i++) // Item list
    {
      System.out.println(item[i]);
    }

    // User input for items
    System.out.println("Please enter the item that you wish to purchase: ");
    boolean flag = true; // for running while loop
    while (flag) {
      try {
        System.out.println("(Please enter a number between 0 and 4)");
        itemIndex = Integer.parseInt(input.nextLine());
        itemsPurchased[0][0] = item[itemIndex];
        itemsPurchased[0][1] = price[itemIndex];
        flag = false;
      }
      catch (Exception e){
        System.out.println("Wrong input, please try again.");
        flag = true;
      }
    }
    
    flag = true;
    while (flag) {
      try {
        System.out.println("Please enter the quantity of the item.");
        itemsPurchased[0][2] = Integer.parseInt(input.nextLine());
        if (itemsPurchased[0][2].length() > 0) {
          flag = false;
        } else {
          System.out.println("Please enter at least 1 or greater.");
        }
      }
      catch (Exception e){
        System.out.println("Wrong input, please try again.");
        flag = true;
      }
    }

    int numOfItems = 1;
    boolean stillBuying = true;
    while (stillBuying && numOfItems < 5) {
      System.out.println("Would you like to purchase another item? Please type 'Yes' or 'No' ");
      String Answer = input.nextLine();
        if (Answer.equals("Yes"))
          {
            flag = true;
            System.out.println("Please enter the item that you wish to purchase: ");
            while (flag) {
              try {
                System.out.println("(Please enter a number between 0 and 4)");
                itemIndex = Integer.parseInt(input.nextLine());
                flag = false;

                for (int i=0; i < numOfItems; i++) {
                  if (item[itemIndex] == items[i]) {
                    System.out.println("You cannot order the same item more than once.");
                    flag = true;
                  }
                }
              }
              catch (Exception e){
                System.out.println("Wrong input, please try again.");
                flag = true;
                  }
                }

            itemsPurchased[numOfItems][1] = price[itemIndex];
            itemsPurchased[numOfItems][0] = item[itemIndex];
            
            flag = true;
            while (flag) {
              try {
                System.out.println("Please enter the quantity of the item.");
                itemsPurchased[numOfItems][2] = Integer.parseInt(input.nextLine());
                if (itemsPurchased[numOfItems][2].length() > 0) {
                  flag = false;
                } else {
                  System.out.println("Please enter at least 1 or greater.");
                }  
              }
              catch(Exception e){
                System.out.println("Wrong input, please try again.");
                flag = true;
              }
              numOfItems++;
            }
          }
        else if (Answer.equals("No"))
          {
            stillBuying = false;
          }
        else
          {
            System.out.println("You didn't enter yes or no fam.");
          }
      
    }
  

  // calculation of subtotal
  for (int i=0; i<5; i++) {
    SubTotal = SubTotal + quantity[i] * prices[i];
  }

  TotalCost = TaxCalc(SubTotal);
  
  // Output screen (writing to the file)
  FileWriter ordeWriter = new FileWriter("orderinfo.txt");
  orderFile.write(
    System.out.println("\nThank you for purchasing " + FirLasName);
    System.out.println("-----------------------------------------");
    System.out.println("Price\t \t \tItem");

    for (int j=0; j<5; j++) {
      for (int k = 0; k < itemsPurchased[j][2].length(); k++)
      {
        System.out.print(prices[j]);
        System.out.print("\t \t \t" + itemsPurchased[j][2]);
        System.out.print("\n");
      }
    }
    
    System.out.println("-----------------------------------------");
    System.out.println("Total cost\t \t \t" + SubTotal);
    System.out.println("After tax\t \t \t" + String.format("%.2f", TotalCost));
  );
  orderFile.close();
  }
}