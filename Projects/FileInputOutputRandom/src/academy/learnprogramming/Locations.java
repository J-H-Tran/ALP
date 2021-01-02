package academy.learnprogramming;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        try (RandomAccessFile rao = new RandomAccessFile("location_rand.dat", "rwd")) {
            rao.writeInt(locations.size());                         // 3, because our index contains: locationID, offset, length/size of record
            int indexSize = locations.size() * 3 * Integer.BYTES;   // (number of locations)(ints contained in ea record)(size of Integer)
            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);   // calculate current position of file pointer, account for the Integer we're about to write
            rao.writeInt(locationStart);

            long indexStart = rao.getFilePointer();

            int startPointer = locationStart;   // set the offset for the first location to a variable, used to calculate the location record's length after writing to file
            rao.seek(startPointer); //used to move the file pointer to the first location's offset, only needed for first location because after it's just sequential data processing

            for (Location location : locations.values()) {
                rao.writeInt(location.getLocationID());
                rao.writeUTF(location.getDescription());
                StringBuilder s = new StringBuilder();

                for (String direction : location.getExits().keySet()) {
                    if (!direction.equalsIgnoreCase("Q")) {
                        s.append(direction)
                                .append(",")
                                .append(location.getExits().get(direction))
                                .append(",");
                    }
                }

                rao.writeUTF(s.toString()); // write data, writing the exits

                IndexRecord record = new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));    // create IndexRecord, length = current position - start position
                index.put(location.getLocationID(), record);    // add IndexRecord for the location using locationID as key

                startPointer = (int) rao.getFilePointer();      // update start pointer to next position
                // we wrote the location to the file and we can now write our index
            }

            rao.seek(indexStart);   // seeking offset we saved previously on line 26, we're going back to that location again
            for (Integer locationID : index.keySet()) { // looping through all index records and writing them to file
                rao.writeInt(locationID);
                rao.writeInt(index.get(locationID).getStartByte());
                rao.writeInt(index.get(locationID).getLength());
            }
        }
    }

    // Bytes (0-3) First 4 bytes will contain the number of locations
    // Bytes (4-7) Next 4 bytes will contain the start offset of the locations section
    // Bytes (8-1699) Contains the index
    // Byte 1700 Final section of the file will contain the locations records (the data)
    static {
        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
            boolean eof = false;

            while (!eof) {
                try {
                    Location location = (Location) locFile.readObject();
                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
                    System.out.println("found " + location.getExits().size() + " exits");

                    locations.put(location.getLocationID(), location);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (InvalidClassException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        } catch (IOException io) {
            System.out.println("IOException " + io.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException " + e.getMessage());
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
/*
* Notes: for reference, cleaned up main method to look cleaner
* */
//    public static void main(String[] args) throws IOException {
//        // try with resources ensures that FileWriter stream is closed whether the code executes normally or an Exception occurs
////        try (BufferedWriter locFile = new BufferedWriter(new FileWriter("locations.txt"));
////             BufferedWriter dirFile = new BufferedWriter(new FileWriter("directions.txt"))) {
////            for (Location location : locations.values()) {
////                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
////
////                for (String direction : location.getExits().keySet()) {
////                    if (!direction.equalsIgnoreCase("Q")) {
////                        dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
////                    }
////                }
////            }
////        }
//        /*
//        * try-finally does behave differently than try-with-resources
//        * the try-finally can throw at the .close() back up the call stack
//        * but the above version causes the exception to be suppressed and the exception from the try block is the one thrown up the call stack
//        * possible in the try-finally first error could be hidden by the exception when closing the stream, close() probably not the root cause of error
//        * try-with-resources ensures that the first error is the one being thrown back
//        **/
//
////        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
////            for (Location location : locations.values()) {
////                locFile.writeInt(location.getLocationID());
////                locFile.writeUTF(location.getDescription());
////                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
////
////                locFile.writeInt(location.getExits().size() - 1);
////                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
////
////                for (String direction : location.getExits().keySet())
////                    if (!direction.equalsIgnoreCase("Q")) {
////                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
////                        locFile.writeUTF(direction);
////                        locFile.writeInt(location.getExits().get(direction));
////                    }
////            }
////        }
//
//        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {
//            for (Location location : locations.values()) {
//                locFile.writeObject(location);
//            }
//        }
//
//    }
//
//    /*
//    * Static initialization block is initialized when the class is loaded so there isn't a way for code to catch
//    * an exception anywhere if it occurs in this block.
//    *
//    * You can throw unchecked exceptions in an initialization block but not checked ones
//    * */
//    static { // static block ensures data is initialized only once, can be used throughout the class. Runs before main method()
////        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
////            boolean eof = false;
////
////            while(!eof) {
////                try {
////                    Map<String, Integer> exits = new LinkedHashMap<>();
////                    int locID = locFile.readInt();
////                    String description = locFile.readUTF();
////                    int numExits = locFile.readInt();
////                    System.out.println("Read location " + locID + " : " + description);
////                    System.out.println("Found " + numExits + " exits");
////
////                    for (int i = 0; i < numExits; i++) {
////                        String direction = locFile.readUTF();
////                        int destination = locFile.readInt();
////                        exits.put(direction, destination);
////                        System.out.println("\t\t" + direction + "," + destination);
////                    }
////                    locations.put(locID, new Location(locID, description, exits));
////                } catch (EOFException e) {
////                    eof = true;
////                }
////            }
////        } catch (IOException e) {
////            System.out.println("IOException");
////        }
//
//        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))) {
//            boolean eof = false;
//
//            while (!eof) {
//                try {
//                    Location location = (Location) locFile.readObject();
//                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
//                    System.out.println("found " + location.getExits().size() + " exits");
//
//                    locations.put(location.getLocationID(), location);
//                } catch (EOFException e) {
//                    eof = true;
//                }
//            }
//        } catch (InvalidClassException e) {
//            System.out.println("InvalidClassException " + e.getMessage());
//        } catch (IOException io) {
//            System.out.println("IOException " + io.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("ClassNotFoundException " + e.getMessage());
//        }
//
//
//            /*
//            * Why don't we need to worry about closing FileReader stream?
//            *
//            * When the scanner is closed its close() method also takes care of closing any stream that it was using
//            * provided that the stream object implements the closable interface and the FileReader does.
//            * It's probably more accurate to refer to it as readable instead of a stream because, the source for a
//            * scanner must be an object that implements the readable interface.
//            * */
////        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))) {
////            scanner.useDelimiter(",");
////            while (scanner.hasNextLine()) {
////                int loc = scanner.nextInt();
////
////                scanner.skip(scanner.delimiter());  // skip over a delimiter when parsing data from file
////                String description = scanner.nextLine();
////
////                System.out.println("Imported loc: " + loc + ": " + description);
////
////                Map<String, Integer> tempExit = new LinkedHashMap<>();
////                locations.put(loc, new Location(loc, description, tempExit));
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        // Reading exits
////        try (BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))) {
////            String input;
////
////            while ((input = dirFile.readLine()) != null) {
////                String[] data = input.split(",");
////
////                int loc = Integer.parseInt(data[0]);
////                String direction = data[1];
////                int destination = Integer.parseInt(data[2]);
////
////                System.out.println(loc + ": " + direction + ": " + destination);
////                Location location = locations.get(loc);
////                location.addExit(direction, destination);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }