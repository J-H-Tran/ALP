package academy.learnprogramming;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Locations implements Map<Integer, Location> {
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();
    private static RandomAccessFile ra;

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
                        s.append(direction);
                        s.append(",");
                        s.append(location.getExits().get(direction));
                        s.append(",");
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
        try {
            ra = new RandomAccessFile("locations_rand.dat", "rwd"); // opens file
            int numLocations = ra.readInt();    // read num of locations into a variable
            long locationStartPoint = ra.readInt(); // read in offset of location section

            while (ra.getFilePointer() < locationStartPoint) {  // load index into memory, to read the index we loop through until file pointer reaches location's offset
                int locationId = ra.readInt();
                int locationStart = ra.readInt();
                int locationLength = ra.readInt();
                                                // reading index and creating records as we go
                IndexRecord record = new IndexRecord(locationStart, locationLength); // create IndexRecord
                index.put(locationId, record);  // saving IndexRecord
                // numLocations not being used but it's good practice to write the number of records in each file section at the beginning of a file that will be randomly accessed
            }
        } catch (IOException e) {
            System.out.println("IOException in static initializer: " + e.getMessage());
        }
    }

    public Location getLocation(int locationId) throws IOException {
        IndexRecord record = index.get(locationId);
        ra.seek(record.getStartByte()); // moving file pointer to location's offset with seek() after getting startByte
        int id = ra.readInt();  // reads the locationID
        String description = ra.readUTF();  // reads description of each room location. readUTF() knows how much to read because when we wrote the file writeUTF() wrote the length then the string
        String exits = ra.readUTF();    // read exits

        // Extract various comma separated exits
        String[] exitPart = exits.split(",");

        Location location = new Location(locationId, description, null);    // initialize exits Map as empty because we're going to manually add the data into it

        if (locationId != 0) {  // adds exits to a location
            for (int i = 0; i < exitPart.length; i++) {
                System.out.println("exitPart = " + exitPart[i]);
                System.out.println("exitPart + 1 = " + exitPart[i + 1]);

                String direction = exitPart[i];
                int destination = Integer.parseInt(exitPart[++i]);  // we're incrementing it first then getting the element because the exits Map has directions to rooms it exits to
                location.addExit(direction, destination);
                // return location we've built up in this method. So that should now grab the location from the random access file
                // builds up the description and the exits, creates location object, and then adds various exits to the location and returns that
            }
        }
        return location;
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

    public void close() throws IOException {
        ra.close();
    }
}
