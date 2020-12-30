package academy.learnprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {
        HeavenlyBody temp = new HeavenlyBody("Mercury", 88);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Venus", 225);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Earth", 365);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        HeavenlyBody tempMoon = new HeavenlyBody("Moon", 27);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        temp = new HeavenlyBody("Mars", 687);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        tempMoon = new HeavenlyBody("Deimos", 1.3);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        tempMoon = new HeavenlyBody("Phobos", 0.3);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        temp = new HeavenlyBody("Jupiter", 4332);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        tempMoon = new HeavenlyBody("Io", 1.8);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        tempMoon = new HeavenlyBody("Europa", 3.5);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        tempMoon = new HeavenlyBody("Ganymede", 7.1);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        tempMoon = new HeavenlyBody("Callisto", 16.7);
        solarSystem.put(tempMoon.getName(), tempMoon);
        temp.addMoon(tempMoon);

        temp = new HeavenlyBody("Uranus", 30660);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Naptune", 165);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        temp = new HeavenlyBody("Pluto", 248);
        solarSystem.put(temp.getName(), temp);
        planets.add(temp);

        System.out.println("Planets");
        for (HeavenlyBody planet : planets) {
            System.out.println("\t" + planet.getName());
        }

        HeavenlyBody body = solarSystem.get("Earth");
        System.out.println("Moons of " + body.getName());
        for (HeavenlyBody moons : body.getSatellites()) {
            System.out.println("\t" + moons.getName());
        }

        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody planet : planets) {
            moons.addAll(planet.getSatellites());
        }

        System.out.println("All Moons");
        for (HeavenlyBody moon : moons) {
            System.out.println("\t" + moon.getName());
        }

        Object o = new Object();
        o.equals(o);
        "pluto".equals("");

    }
}

/*
 * In general, Sets are used less often than List or Map but they can be extremely useful.
 *
 * List: a collection of ordered items and can contain duplicates
 * Set: has no defined ordering and no duplicates
 *
 * The lack of duplicates is an important differentiator as there are ordered Sets such as,
 * LinkedHashSet and TreeSet
 *
 * To ensure that there are no duplicates in your Collection a good way of doing that is to use a Set rather than a List
 * The Set Interface, just like everything else in the Collections Framework, is Generic and it takes a single type
 * Just like Map and List, it's possible to create a Raw set but this is mainly intended for backwards compatibility.
 * before Generics were added to Java and generally, it's not a good idea to use Raw types.
 *
 * Set Interface defines basic methods of add(), remove(), and clear() to maintain the items in the set
 * there's also, size(), isEmpty(), and contains() to provide info about what's in the Set.
 * Interestingly, there is no way to retrieve an item from a Set. You can check if it exists and can iterate over
 * a Set but attempting to get the nth element from a Set is impossible you'd need to use a List to do something like that.
 *
 * Keys in a map can be retrieved as a Set using the keySet(). Everything said about keys in a Map also applies to
 * items in a Set. Using mutual objects as elements in a Set can cause problems and the behavior is undefined if
 * changing an object affects equals() comparisons.
 *
 * Just as a Map cannot contain itself as a key a Set cannot be an element of itself.
 * The best performing implementation of the Set Interface is the HashSet Class that uses hashes to store items.
 * Like the HashMap class, in fact, the HashSet implementation currently uses a HashMap.
 *
 * Knowing that a Set can be implemented using a Map, then it's not hard to see that you could actually use the
 * corresponding Map obj and use only the keys ignoring the values. This is what HashSet does whenever an element
 * is added to a Set it becomes a key in the underlying HashMap and a dummy obj is stored as the value.
 *
 * Sets can be useful because operations on them can be very fast and if you're dealing with the mathematical notion
 * of a Set, then the Java Collections' Set types allows the usual set operations such as Union and Intersection.
 * Can apply Set theory to sets addAll() -> Union of multiple sets
 * */

/*
* It is possible to add an object with a key value pair that allows a duplicate key with a different value if we're not careful.
* The Set, or Map, will allow this because, although the 'key' may be the same the object had a different instantiation
* so, it's the same 'key-but-different-value' pair in a separate object, and therefore has a different reference.
* The default equals() will do a referential comparison of == and see that they are different but the key, to us, is
* a duplicate. This is why we should override the equals() and provide our implementation for equality comparison.
*
* When storing objects in a hashed Collection we can think of the collection as having a number of buckets to store
* the objects in. hashcode determines which bucket the objects go into. There is a requirement that any objects that
* are equal should always have the same hash code and ultimately end up in the same bucket but the opposite isn't always
* true. So, two objects that are equal do not have to have different hash codes... Wutface
*
* Using the equals() the collection will only know it's there if it's looking in the right bucket so the hash code
* must be the same. Then equals() will return true. If the collection is checking the wrong bucket meaning, if the
* hash code for the new object is not the same as an object that it is equal to, that's where we can have problems
* because the new object will then be added to the collection. And we try to remove/delete it we may not get rid of
* the right one.
*
* There has to be a strict relationship between hashCode() and equals() so that 2 equal objects, when compared, will
* have the same hashCode.
* */
