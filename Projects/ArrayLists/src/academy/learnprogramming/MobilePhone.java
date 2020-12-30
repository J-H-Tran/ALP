package academy.learnprogramming;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class MobilePhone {

    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<Contact>();
    }

    //Add new contact to phone
    public boolean addNewContact(Contact contact) {

        if(findContact(contact.getName()) >= 0) {
            System.out.println("Contact already exists.");
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int foundPosition = findContact(oldContact);

        if(foundPosition < 0) {
            System.out.println(oldContact.getName() + " was not found.");
            return false;
        }

        this.myContacts.set(foundPosition, newContact);
        System.out.println(oldContact.getName() + " was repalced with " + newContact.getName());
        return true;
    }

    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);

        if(foundPosition < 0) {
            System.out.println(contact.getName() + " was not found.");
            return false;
        }
        System.out.println(contact.getName() + " was removed.");
        this.myContacts.remove(foundPosition);
        return true;
    }

    //Overload findContact()
    //private: only used within this class
    private int findContact(Contact contact) {
        return this.myContacts.indexOf(contact);
    }

    private int findContact(String contactName) {

        OptionalInt indexOpt = IntStream
                .range(0, this.myContacts.size())
                .filter(i -> contactName.equals(this.myContacts.get(i)))
                .findFirst();

        if (indexOpt.isPresent()) {
            return Integer.parseInt(indexOpt.toString());
        }
        else {
            return -1;
        }
    }

    public String queryContact(Contact contact) {

        if(findContact(contact) >= 0) {
            return contact.getName() + " was found";
        }

        return null;
    }

    public void printContacts() {
        this.myContacts
                .forEach(x -> {
                    System.out.println("Name: " + x.getName() + " -> "
                            + "Number: " + x.getPhoneNumber());
                });
    }
}
