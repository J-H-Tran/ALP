package academy.learnprogramming;

import java.util.*;

public class Theater {
    private final String theaterName;
    private List<Seat> seats = new ArrayList<>();

    /*
    * It may look like we're instantiating the Comparator Interface but this is actually an anonymous inner class
    * implementing Comparator and it's providing an implementation for the single compare() method.
    * It's a rather simple method that returns the same results as the compareTo() method does.
    *
    * Note: There is a major issue to be aware of when using the anonymous inner class Comparator for comparing
    * !!" and ordering being consistent with equals()" - A method that produces ordering that is consistence with equals()
    * will only return 0 if the elements being compare are actually equal
    *
    * We can add as many Comparators as we want. It doesn't have to be static but it makes it easier to use if we don't
    * need a class instance in order to invoke it. In the example we made it a static variable of the Theater class
    * that is because it's controlling the access to Seats
    * */
    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        /*
        * this compare() method is not good enough for sorting based on just price since there are other things to
        * consider when ordering Seat objects in this case.
        * */
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()) {
                return -1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public Theater(String theaterName, int numRows, int seatsPerRow) {
        this.theaterName = theaterName;

        // creates List of seats starting
        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.00;

                if ((row < 'D') && (seatNum >= 4 && seatNum <= 9)) {
                    price = 14.00;
                } else if ((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }

                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheaterName() {
        return theaterName;
    }

    public boolean reserveSeat(String seatNum) {
        Seat requestedSeat = new Seat(seatNum, 0.00);
        // Binary Search: fastest way to find an element in a sorted list
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("There is no seat " + seatNum);
            return false;
        }

//        for (Seat seat: seats) {
//            if (seat.getSeatNumber().equals(seatNum)) {
//                requestedSeat = seat;
//                break;
//            }
//        }
//
//        if (requestedSeat == null) {
//            System.out.println("There is no seat " + seatNum);
//            return false;
//        }
//
//        return requestedSeat.reserve();
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, Double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if (this.reserved) {
                this.reserved = false;
                System.out.println("Reservation for seat " + seatNumber + " cancelled.");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }
    }
}
