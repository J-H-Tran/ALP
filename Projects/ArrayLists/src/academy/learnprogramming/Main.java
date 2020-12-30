package academy.learnprogramming;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("1+832 475 0853");

    public static GroceryList groceryList = new GroceryList();
    public static void addNewContact() {
        String name = scanner.nextLine();
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber);
    }

    public static void main(String[] args) {

        boolean quit = false;
//        startPhone();
//        printActions();
//        while(!quit) {
//
//        }

        Contact jonathan = new Contact("JT", "911");
        Contact james = new Contact("James", "912");
        Contact jenny = new Contact("Jenny", "913");
        Contact henry = new Contact("Henry", "999");
        mobilePhone.addNewContact(jonathan);
        mobilePhone.addNewContact(james);
        mobilePhone.addNewContact(jenny);
        mobilePhone.printContacts();
        System.out.println("End of first print. Added 3 contacts\n");

        quit = mobilePhone.updateContact(jonathan, henry);
        mobilePhone.printContacts();
        System.out.println("End of second print. Updated contact\n");

        quit = mobilePhone.removeContact(jenny);
        mobilePhone.printContacts();
        System.out.println("End of third print. Removed contact\n");

        System.out.println(mobilePhone.queryContact(henry));
        mobilePhone.printContacts();
        System.out.println("End of final print. Searched for contact\n");
/*
        boolean quit = false;
        int choice = 0;

        while(!quit) {

            printInstructions();
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    groceryList.printGroceryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    findItem();
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
*/
    }

    public static void printInstructions() {
        System.out.println("Press \n" +
                "0 - View choice options\n" +
                "1 - Print list of items\n" +
                "2 - Add item to list\n" +
                "3 - Modify item on list\n" +
                "4 - Remove item from list\n" +
                "5 - Search for item on list\n" +
                "6 - Quit the application");
    }

    public static void addItem() {
        System.out.println("Add an item");
        groceryList.addGroceryItem(scanner.nextLine());
    }

    public static void modifyItem() {
        System.out.println("Enter item you want replaced");
        String itemNo = scanner.nextLine();

        System.out.println("Replace with item");
        String newItem = scanner.nextLine();
        groceryList.modifyGroceryItem(itemNo, newItem);

    }

    public static void removeItem() {
        System.out.println("Enter item name");
        String itemNo = scanner.nextLine();
        groceryList.removeGroceryItem(itemNo);
    }

    public static void findItem() {
        System.out.println("Find item");
        String searchItem = scanner.nextLine();

        if(groceryList.onFile(searchItem)) {
            System.out.println("Found " + searchItem + " in list");
        } else {
            System.out.println(searchItem + " is not in the list");
        }

    }
}
