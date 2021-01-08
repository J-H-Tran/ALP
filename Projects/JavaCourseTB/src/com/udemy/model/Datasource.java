package com.udemy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datasource { // often used as a Singleton
    // list of constants can get very large, typically put in their own classes
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\JavaMasterclassBuchalka\\Projects\\JavaCourseTB\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONG_ID = "_id";
    public static final String COLUMN_SONG_TRACK = "track";
    public static final String COLUMN_SONG_TITLE = "title";
    public static final String COLUMN_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String QUERY_ALBUMS_BY_ARTIST_START =
            "select " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
                    + " from " + TABLE_ALBUMS + " inner join " + TABLE_ARTISTS
                    + " on " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID
                    + " where " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT =
            " order by " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " collate nocase ";

    public static final String QUERY_ARTIST_FOR_SONG_START =
            "select " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
                    + ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK
                    + " from " + TABLE_SONGS
                    + " inner JOIN " + TABLE_ALBUMS
                    + " on " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID
                    + " inner join " + TABLE_ARTISTS
                    + " on " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID
                    + " where " + TABLE_SONGS + "." + COLUMN_SONG_TITLE + " = \"";
    public static final String QUERY_ARTIST_FOR_SONG_SORT =
            " order by " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " collate nocase ";

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to DB: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtists(int sortOrder) {

        StringBuilder sb = new StringBuilder("select * from ");
        sb.append(TABLE_ARTISTS); // use const for building SQL queries, prevent SQL Injection
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" order by ");
            sb.append(COLUMN_ARTIST_NAME);
            sb.append(" collate nocase ");

            if (sortOrder == ORDER_BY_DESC) {
                sb.append("desc");
            } else {
                sb.append("asc");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) { // use indices when using ResultSet.get()
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(INDEX_ARTIST_ID));
                artist.setName(resultSet.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    //    select albums.name from albums
//    inner join artists on albums.artist = artists._id
//    where artists.name ='Carole King' order by albums.name collate nocase asc;
    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);

            if (sortOrder == ORDER_BY_DESC) {
                sb.append("desc");
            } else {
                sb.append("asc");
            }
        }

        System.out.println("SQL statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<String> albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(resultSet.getString(1));
            }
            return albums;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_SORT);

            if (sortOrder == ORDER_BY_DESC) {
                sb.append("desc");
            } else {
                sb.append("asc");
            }
        }
        System.out.println("SQL Statement: "+sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString())) {

            List<SongArtist> songArtists = new ArrayList<>();
            while (resultSet.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void querySongsMetadata() {
        String sql = "select * from " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData meta = resultSet.getMetaData();

            int numCols = meta.getColumnCount();
            for (int i = 1; i <= numCols; i++) {
                System.out.format("Column %d in the songs table is named %s\n", i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public int getCount(String table) {
        // good practice to assign names as the resulting columns using AS
        String sql = "select count(*) as count from " + table;
        try (Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {

            int cnt = resultSet.getInt("count"); // passing alias column name rather than the column index 1
            System.out.format("Count = %d\n", cnt);
            return cnt;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }
}
/*In a large enterprise application we may create a class in the model package for each table
 * and the connections might be coming from a connection pool but for this small application
 * we'll keep our db methods in our Datasource class
 *
 * We don't want to return a ResultSet from our methods because we don't want classes that use
 * methods in the model package to have to understand the implementation detials of the model.
 * we need an alternative way to returnthe information. We'll return a List of artists and that
 * means we're going to need classes for artists, albums, songs.
 * */
