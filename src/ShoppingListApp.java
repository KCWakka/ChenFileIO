import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingListApp {

    private ArrayList<Item> shoppingList;
    private Scanner scan;

    public ShoppingListApp() {
        shoppingList = new ArrayList<>();
        scan = new Scanner(System.in);
    }

    public void start() {

        readData();  // read data in from the file

        System.out.println("---------------------------------");
        System.out.println("Welcome to the shopping list app!");
        System.out.println("---------------------------------");
        String option = "";
        while (!option.equals("q")) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("(v)iew shopping list");
            System.out.println("(a)dd item to list");
            System.out.println("(e)dit an item's price");
            System.out.println("(r)emove item from list");
            System.out.println("(q)uit");
            System.out.print("Enter choice: ");
            option = scan.nextLine();
            if (option.equals("v")) {
                viewShoppingList();
            } else if (option.equals("a")) {
                addItemToList();
            } else if (option.equals("e")) {
                editItemPrice();
            } else if (option.equals("r")) {
                removeItem();
            }
            saveData();
        }
        System.out.println("Goodbye!");
    }

    private void readData() {
        // TODO: write this method: load the shopping list data from your shoppinglist.txt file and populate shoppingList.
        //  note that this method gets called immediately at the start of the "start" method;
        //  you only need to read the data in one time to populate the shoppingList arraylist
        try {
            File myfile = new File("src\\shoppinglist.txt");
            Scanner fileScanner = new Scanner(myfile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String itemName = splitData[0];
                double itemPrice = Double.parseDouble(splitData[1]);
                Item item = new Item(itemName, itemPrice);
                shoppingList.add(item);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void saveData() {
        // TODO: write this method: save the items in shoppingList to shoppinglist.txt
        try {
            PrintWriter dataFile = new PrintWriter("src\\shoppinglist.txt");
            for (Item item : shoppingList) {
                dataFile.println(item.getName() + "," + item.getPrice());
            }
            dataFile.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void viewShoppingList() {
        // TODO: write this method: it should display all items on shoppingList, unless the list is empty,
        //  in which case, print "shopping list empty"
        //  (print out from the arraylist; you don't need to read the data back in again)
        if (shoppingList.size() == 0) {
            System.out.println("shopping list empty");
        } else {
            double total = 0;
            for (Item item : shoppingList) {
                System.out.println(item.getName() + " " + item.getPrice());
                total += item.getPrice();
            }
            System.out.println("Items in the shopping list: " + shoppingList.size());
            System.out.println("Total price of the shopping list: " + total);
        }
    }

    private void addItemToList() {
        // TODO: write this method: ask user for item name and price then add that item to shoppingList;
        System.out.print("Enter item's name: ");
        String name = scan.nextLine();
        System.out.print("Enter item's price: ");
        double price = scan.nextDouble();
        scan.nextLine();
        Item item = new Item(name, price);
        shoppingList.add(item);
    }

    private void editItemPrice() {
        // TODO: write this method: ask user for an item's name, then ask for an updated price,
        //  then edit that item's price; if the item isn't found, do nothing and print "item not found"
        System.out.print("Enter name of item to edit: ");
        String name = scan.nextLine();
        boolean has = false;
        int index = 0;
        for (int i = 0; i < shoppingList.size(); i++) {
            if (shoppingList.get(i).getName().equals(name)) {
                has = true;
                index = i;
            }
        }
        if (has) {
            System.out.print("Enter item's new price: ");
            double price = scan.nextDouble();
            scan.nextLine();
            Item item = new Item(name, price);
            shoppingList.set(index, item);
        } else {
            System.out.println("item not found");
        }
    }

    private void removeItem() {
        // TODO: write this method: ask user for an item's name, then remove that item from shoppingList;
        //  if the item isn't found, do nothing and print "item not found"
        System.out.print("Enter name of item to edit: ");
        String name = scan.nextLine();
        boolean has = false;
        int index = 0;
        for (int i = 0; i < shoppingList.size(); i++) {
            if (shoppingList.get(i).getName().equals(name)) {
                has = true;
                index = i;
            }
        }
        if (has) {
            shoppingList.remove(index);
        } else {
            System.out.println("item not found");
        }
    }
}
