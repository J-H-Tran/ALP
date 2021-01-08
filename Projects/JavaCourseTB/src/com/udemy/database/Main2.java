package com.udemy.database;

import com.udemy.model.Artist;
import com.udemy.model.Datasource;
import com.udemy.model.SongArtist;

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

        List<String> albumsForArtists = datasaource.queryAlbumsForArtist("Iron Maiden", Datasource.ORDER_BY_ASC);
        for (String album : albumsForArtists) {
            System.out.println(album);
        }

        List<SongArtist> songArtists = datasaource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find any artists for song");
            return;
        }

        for (SongArtist songArtist : songArtists) {
            System.out.println("Artist name: " + songArtist.getArtistName()
                    + " Album name: " + songArtist.getAlbumName()
                    + " Track no: " + songArtist.getTrack());
        }

        datasaource.querySongsMetadata();

        int count = datasaource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        datasaource.createViewForSongArtist();

        /*Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a song title: ");
        String title = scnr.nextLine();

        songArtists = datasaource.querySongInfoView(title);
        if (songArtists.isEmpty()) { // should really be using isEmpty() rather than checking for null
            System.out.println("Couldn't find artist for song");
            return;
        }
        for (SongArtist songArtist : songArtists) {
            System.out.println("From view: Artist name=" + songArtist.getArtistName() + ", Album name=" + songArtist.getAlbumName()
                    + ", Track number=" + songArtist.getTrack());
        }*/

        datasaource.insertSong("YOYO", "YOYO", "YOYO", 1);

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
 *
 * Can query metadata from ResultSet since we might not be able to get the schema with certain DBs
 * */

