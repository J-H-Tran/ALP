package academy.learnprogramming;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Button btnPrint = new Button("Print");

    public static void main(String[] args) {
	// write your code here

/*
        GearBox mcLaren = new GearBox(6);

        //Now, to create a Gear class we have to use GearBox
        //the .new needs to refer to an outerclass, an instance of GearBox
        //Probably don't want users to access Gear in the first place so we may want to make it private
*/
/*
        GearBox.Gear first = mcLaren.new Gear(1, 12.3);

        System.out.println(first.driveSpeed(1000));*//*
*/
/*
        mcLaren.addGear(1, 5.3);
        mcLaren.addGear(2, 10.6);
        mcLaren.addGear(3, 15.9);

        mcLaren.operateClutch(true);

        mcLaren.changeGear(1);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(1000));
        mcLaren.changeGear(2);
        System.out.println(mcLaren.wheelSpeed(3000));
        mcLaren.operateClutch(true);
        mcLaren.changeGear(3);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(6000));
*/

        /*
        * local class, applicable to this block only
        * could be useful when you want to assign exactly the same object to several buttons
        * Class is not used anywhere else in a shared environment so making it local is fine
        * */
/*
        class ClickListener implements Button.OnClickListener {
            public ClickListener() {
                System.out.println("I've been attached");
            }

            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked.");
            }
        }
        //Attach setOnClickListener to btnPrint field
        btnPrint.setOnClickListener(new ClickListener());

*/
        /*
        * local class above has name ClickListener but this anonymous class does not
        * used the new Button.OnClickListener() implemented in the body
        * override comes from having to implemented the method from interface
        * the new obj has no name and it's declared in the expression and passed as a parameter
        * to the button's setOnClickListener() method
        * */
        btnPrint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked");
            }
        });
        listen();
    }

    private static void listen() {
        boolean quit = false;
        while(!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    btnPrint.onClick();
            }
        }
    }
}
