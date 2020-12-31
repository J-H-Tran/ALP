package academy.learnprogramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // try with resources ensures that FileWriter stream is closed whether the code executes normally or an Exception occurs
        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations2.txt"));
             BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions2.txt"))) {
            for (Location location : locations.values()) {
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");

                for (String direction : location.getExits().keySet()) {
                    dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                }
            }
        }
        // try-finally does behave differently than try-with-resources
        // the try-finally can throw at the .close() back up the call stack
        // but the above version causes the exception to be suppressed and the exception from the try block is the one thrown up the call stack
        // possible in the try-finally first error could be hidden by the exception when closing the stream, close() probably not the root cause of error
        // try-with-resources ensures that the first error is the one being thrown back

    }

    /*
    * Static initialization block is initialized when the class is loaded so there isn't a way for code to catch
    * an exception anywhere if it occurs in this block.
    *
    * You can throw unchecked exceptions in an initialization block but not checked ones
    * */
    static { // static block ensures data is initialized only once, can be used throughout the class
            /*
            * Why don't we need to worry about closing FileReader stream?
            *
            * When the scanner is lcosed its close() method also takes care of closing any stream that it was using
            * provided that the stream object implements the closable interface and the FileReader does.
            * It's probably more accurate to refer to it as readbale instead of a stream because, the source for a
            * scanner must be an object that implements the readable interface.
            * */
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();

                scanner.skip(scanner.delimiter());  // skip over a delimiter when parsing data from file
                String description = scanner.nextLine();

                System.out.println("Imported loc: " + loc + ": " + description);

                Map<String, Integer> tempExit = new HashMap<>();
                locations.put(loc, new Location(loc, description, tempExit));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading exits
        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
            String input;

            while ((input = dirFile.readLine()) != null) {
                String[] data = input.split(",");

                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);

                System.out.println(loc + ": " + direction + ": " + destination);
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
