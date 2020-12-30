package academy.learnprogramming;

import java.lang.reflect.GenericDeclaration;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int value = 3;
        if(value == 1) {
            System.out.println("value is 1");
        } else if(value ==2) {
            System.out.println("value is 2");
        } else {
            System.out.println("value is not 1 or 2");
        }


        int switchValue = 1;

        switch(switchValue) {
            case 1:
                System.out.println("value is 1");
                break;
            case 2:
                System.out.println("value is 2");
                break;
            case 3:
                System.out.println("value is 3");
                break;
            default:
                System.out.println("Value is not 1 or 2 or 3");
                break;

        }
    }
}
