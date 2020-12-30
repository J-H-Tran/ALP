package academy.learnprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Theater theater = new Theater("Olympian", 8, 12);
//        theater.getSeats();

        if (theater.reserveSeat("D12")) {
            System.out.println("Please pay for D12");
        } else {
            System.out.println("Sorry, seat is reserved");
        }

        if (theater.reserveSeat("B13")) {
            System.out.println("Please pay for B13");
        } else {
            System.out.println("Sorry, seat is reserved");
        }

        List<Theater.Seat> reverseSeats = new ArrayList<>(theater.getSeats());
        Collections.reverse(reverseSeats);
        printList(reverseSeats);

        List<Theater.Seat> priceSeats = new ArrayList<>(theater.getSeats());
        priceSeats.add(theater.new Seat("B00", 13.00));
        priceSeats.add(theater.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theater.PRICE_ORDER);
        printList(priceSeats);

        /*
        * There is another way to use the sort() method and that is to pass it a Comparator
        * it's similar to Comparable, the Comparator Interface defines a single method called compare()
        * unlike comparable, the objects to be sorted don't have to implement comparable.
        * Instead, an object of type comparator can be created with a compare() method that can sort the objects
        * that we're interested in.
        *
        * More than 1 comparator can be created allowing for objects to be sorted in different ways.
        * We can either create a comparator object within an existing class or we could create a new class that
        * implements the comparator interface.
        * */

//        Theater theater = new Theater("Olympian", 8, 12);
        /*
        * The seatCopy list contains all the elements that are in the original, the one instantiated by theater above.
        * The approach used to create seatCopy and populate all the elements in Theater.Seat is called shallow copy
        * It creates an ArrayList containing all the elements from the List that were passed to the constructor
        *
        * Note: elements aren't really copied. If we modified one of the seats, like calling reserve(), then that
        * change will be seen in either list we check for. We may have a new updated list but the contents are the same
        * objects that exist in Theater.Seat List -> seatCopy
        *
        * We're taking a shallow copy and putting it into an ArrayList but they are effectively the same objects
        * */
//        List<Theater.Seat> seatCopy = new ArrayList<>(theater.seats);

        // We have 2 different Lists but they both contain references to the same Seat objects
        /*printList(seatCopy);

        seatCopy.get(1).reserve();
        if (theater.reserveSeat("A02")) {
            System.out.println("Please pay for seat");
        } else {
            System.out.println("Seat already reserved.");
        }

        Collections.shuffle(seatCopy);
        System.out.println("Printing seatCopy");
        printList(seatCopy);

        System.out.println("Printing theater.seats");
        printList(theater.seats);*/

    }

    public static void printList(List<Theater.Seat> list) {

        for (Theater.Seat seat : list) {
            System.out.print(" " + seat.getSeatNumber() + " $" + seat.getPrice());
        }
        System.out.println("\n=====================================================================================");
    }
}
