package com.udemy.database;

import com.udemy.model.Artist;
import com.udemy.model.Datasource;

import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        Datasource datasaource = new Datasource();

        if (!datasaource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasaource.queryArtists(Datasource.ORDER_BY_ASC);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        }

        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
        }

        datasaource.close();
    }
}
/*
* Note that the main() doesn't make any assumptions about how or where the data is stored
* The data could be from xml file, spreadsheet, DB, or flat file.
* If we change how and where the data is stored, as long as we don't have to change any of
* the method signatures in the Datasource class, then we won't have to change any classes
* that use it. In this case the main() will remain unchanged.
*
* We don't have to explicitly close ResultSet, closing Statement would ultimately close ResultSet
* There's an optimization we can make, right now we're using column names to get the field
* values, but we can use the column index instead and that's usually more efficient.
* There is a tradeoff, if we use column names we won't have to change any code if the positions
* of the columns change within the table.
* If we use column indices we would have to change them.
*
* This is handled if we use constants for our column indices, which we'll do.
* The index we pass to the ResultSet is the index of the column in the ResultSet, not the table.
*
* Column indices are 1 based, unlike Java.
*
* It's more efficient to use the column index because the getter methods will know exactly where
* to go to get the value in the ResultSet. When we use the column name the method has to match
* the column name against the columns in the ResultSet.
* */

