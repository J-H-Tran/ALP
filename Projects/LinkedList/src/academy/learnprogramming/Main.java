package academy.learnprogramming;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer("JT", 99.98);
        Customer anotherCustomer;

        anotherCustomer = customer;
        anotherCustomer.setBalance(11.98);
        System.out.println("Balance for customer " + customer.getName() + " is " + customer.getBalance());

        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();

        integerArrayList.add(1);    //index 0
        integerArrayList.add(3);    //index 1
        integerArrayList.add(4);    //index 2

        integerArrayList.forEach(System.out::println);
        System.out.println("*******\n");
        integerArrayList.add(1,2);  //adds the value: 2 at index: 1

        integerArrayList.forEach(System.out::println);

        ITelephone myPhone;
        myPhone = new HomePhone(12345);
        myPhone.powerOn();
        myPhone.callPhone(12345);
        myPhone.answerPhone();

        myPhone = new CellPhone(12345);
        myPhone.powerOn();
        myPhone.callPhone(12345);
        myPhone.answerPhone();

    }
}
