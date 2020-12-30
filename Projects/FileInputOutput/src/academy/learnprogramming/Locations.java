package academy.learnprogramming;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // try with resources ensures that FileWriter stream is closed whether the code executes normally or an Exception occurs
        try (FileWriter locFile = new FileWriter("locations.txt");
                FileWriter dirFile = new FileWriter("directions.txt")) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");

                for (String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + ", " + direction + ", " + location.getExits().get(direction) + "\n");
                }
            }
        }
        // try-finally does behave differently than try-with-resources
        // the try-finally can throw at the .close() back up the call stack
        // but the above version causes the exception to be suppressed and the exception from the try block is the one thrown up the call stack
        // possible in the try-finally first error could be hidden by the exception when closing the stream, close() probably not the root cause of error
        // try-with-resources ensures that the first error is the one being thrown back

//        FileWriter locFile = null;
//        try {
//            locFile = new FileWriter("locations.txt");
//            for (Location location : locations.values()) {
//                locFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
//            }
//        } finally {
//            System.out.println("in finally block");
//            if (locFile != null) { // important check to only close a file if it's been created otherwise, would throw NullPointerException
//                System.out.println("attempt to close locFile");
//                locFile.close(); // very important to clean up resources
//            }
//        }
    }

    static { // static block ensures data is initialized only once, can be used throughout the class
//        Map<String, Integer> tempExit = new HashMap<>();
//        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 5);
//        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("W", 1);
//        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));
//
//        tempExit = new HashMap<String, Integer>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        locations.put(5, new Location(5, "You are in the forest",tempExit));
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();

    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
