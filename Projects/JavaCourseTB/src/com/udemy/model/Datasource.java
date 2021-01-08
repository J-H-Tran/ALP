package com.udemy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "create view if not exists " + TABLE_ARTIST_SONG_VIEW
            + " as select " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME
            + " as " + COLUMN_SONG_ALBUM + ", " + TABLE_SONGS + "." + COLUMN_SONG_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONG_TITLE
            + " from " + TABLE_SONGS
            + " inner JOIN " + TABLE_ALBUMS
            + " on " + TABLE_SONGS + "." + COLUMN_SONG_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID
            + " inner join " + TABLE_ARTISTS
            + " on " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTIST_ID
            + " order by " + TABLE_ARTISTS + "." + COLUMN_ARTIST_NAME + ", "
            + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", "
            + TABLE_SONGS + "." + COLUMN_SONG_TRACK;

    public static final String QUERY_VIEW_SONG_INFO =
            "select " + COLUMN_ARTIST_NAME + ", " + COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK
                    + " from " + TABLE_ARTIST_SONG_VIEW + " where " + COLUMN_SONG_TITLE + "= \"";

    // select name, album, track FROM artist_list WHERE title = ?, quotation marks aren't needed around '?' DB actually understands that a String is the value
    public static final String QUERY_VIEW_SONG_INFO_PREP =
            "select " + COLUMN_ARTIST_NAME + ", " + COLUMN_SONG_ALBUM + ", " + COLUMN_SONG_TRACK
                    + " from " + TABLE_ARTIST_SONG_VIEW
                    + " where " + COLUMN_SONG_TITLE + " = ?"; // ? placeholder character used in prepared statements

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS +
            '(' + COLUMN_ARTIST_NAME + ") VALUES(?)";

    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS +
            '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(?, ?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS +
            '(' + COLUMN_SONG_TRACK + ", " + COLUMN_SONG_TITLE + ", " + COLUMN_SONG_ALBUM +
            ") VALUES(?, ?, ?)";

    public static final String QUERY_ARTIST = "SELECT " + COLUMN_ARTIST_ID + " FROM " +
            TABLE_ARTISTS + " WHERE " + COLUMN_ARTIST_NAME + " = ?";

    public static final String QUERY_ALBUM = "SELECT " + COLUMN_ALBUM_ID + " FROM " +
            TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    private Connection conn;
    private PreparedStatement querySongInfoView;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            System.out.println("Prepared Query is: " + QUERY_VIEW_SONG_INFO_PREP);
            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            /*
            When we insert an artis or album we need to know what id the '_id' the DB has assigned to the record,
            recall that _id is a PRIMARY_KEY, and the DB auto generates the value whenever it inserts a record into the table.
            So we need to use the artist id when we insert the album and the album id when we insert the song.
            So when we want to get the generated key after a statement is completed we have to add another parameter to
            the Prepared Statement calls.
            artists and albums inserts, we use the IDs we get back from those keys to pass them into another insert statement
            */
            insertIntoArtists = conn.prepareStatement(INSERT_ARTIST, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);

            queryArtist = conn.prepareStatement(QUERY_ARTIST);
            queryAlbum = conn.prepareStatement(QUERY_ALBUM);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to DB: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }
            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if(queryArtist != null) {
                queryArtist.close();
            }
            if(queryAlbum != null) {
                queryAlbum.close();
            }
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
        System.out.println("SQL Statement: " + sb.toString());

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

    public boolean createViewForSongArtist() {
        try (Statement statement = conn.createStatement()) {
            System.out.println("View query: " + CREATE_ARTIST_FOR_SONG_VIEW);
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("Create view failed: " + e.getMessage());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {
        try {
            querySongInfoView.setString(1, title);
            ResultSet resultSet = querySongInfoView.executeQuery();

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

        /*StringBuilder sb = new StringBuilder(QUERY_VIEW_SONG_INFO);
        sb.append(title).append("\"");

        System.out.println(sb.toString());*/

        /*try (Statement statement = conn.createStatement();
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
        }*/
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
 *
 * calling execute() or executeQuery() for every query like this isn't a best practice.
 * Since every call is compiled each time, in an enterprise application there will definitely
 * be performance issues. DB is also vulnerable to hacking attempts.
 *
 * Solution to that? Prepared Statements. Performance improvement to compile a query every time.
 * This handles sql inject by not concatenating Strings together to create sql statements.
 * Provide placeholders in sql statements for values that change from query to query.
 *
 * We need to declare an instance variable for the prepared statement because we only want
 * to create it once, we don't want to create it every time we want to query. We want it to
 * be precompiled once instead of creating a new instance of it every time we want to use it.
 * We lose the performance benefit that a Prepared Statement can provide us.
 *
 * We don't have to worry about closing the ResultSet explicitly when using the PreparedStatement (extends Statement)
 * since it closes automatically when we close the PreparedStatement. So how does this actually work?
 * Value that's being substituted is treated as a literal value, nothing within the value is being treated as
 * SQL. instead of an input being part of the sql statement the literal takes care of any SQL-like characteristics.
 *  where title = "{Go Your Own Way" or 1=1 or "}"
 *  where title = {"Go Your Own Way or 1=1 or "} see's it as an entire string instead of noticing the  quotes as
 * something to interpret.
 * -------------------------------------------------------------------------------------------------------------------
 * PreparedStatement
 * It's good practice to use PreparedStatements because of the potential performance benefit, and because they protect
 * the database against SQL injection attacks.
 * using PreparedStatement:
 * 1. Declare a constant for the SQL statement that contains the placeholders
 * 2. Create a PreparedStatement instance using Connection.prepareStatement(sqlString)
 * 3. When ready to perform (insert, update, delete) we call appropriate setter methods to set the placeholders to the
 *  values we want to use in the statement
 * 4. We run the statement using PreparedStatement.execute() or PrepareStatement.executeQuery()
 * 5. We process the results the same way we do when using a regular old Statement
 *
 * Transactions:
 * Because JDBC Connection Class auto commits changes by default every time we calls execute() to insert, update, or
 * delete records, those changes are saved to the database as soon as the SQL statement completes.
 * Sometimes that's what we want, but often, it isn't.
 *
 * ie.
 * Online Banking,
 * Suppose we want money to transfer from one account to another, it requires 2 sql statements:
 * 1. update source account with new balance
 * 2. update destination account with new balance
 *
 * What would happen if we executed the first statement successfully but the second statement failed?
 * Source has $1000.00 and destination has $100.00 and customer want to transfer $200.00 into the destination account
 * Without errors source would be 800 and destination should be 300.
 * If 1. succeeds and 2. fails, effectively $200 will go missing. What if DB goes down during 2?
 * Integrity of data in DB is compromised.
 *
 * It would be nice if, when we wanted to accomplish something that requires multiple sql statements we could run all
 * the statements as a single unit. Either they would all successfully complete or none of them would.
 * Enter transactions! THEY ENSURE THE INTEGRITY OF THE DATA WITHIN A DB
 *
 * Transaction - A sequence of sql statements that are treated as a single logical unit. If any of the statements fail,
 * the results of any previous statements in the transaction can be rolled bac, or just not saved as if they never happened.
 * DB transactions must be ACID-compliant:
 *  Atomicity - if a series of sql statements change the database, then either all the changes are committed or none of the are
 *  Consistency - before a transaction begins, the DB is in a valid state. When it completes, the DB is still in a valid state.
 *  Isolation - until the changes committed by a transaction are completed they won't be visible to other connections.
 *      Transactions cannot depend on each other
 *  Durability - once the changes performed by a transaction are committed to the DB they're permanent.
 *      If an application then crashes or the DB server does down (in client-server DBs) the changes made by
 *      the transaction are still there when the application runs again or the DB comes back up
 *
 * We only have to use transactions when we change data in the DB. It's not needed when querying the DB since
 * we're not making any changes to the data.
 * No changes can be made to the DB outside of a transaction, every time we use UPDATE, INSERT, and DELETE,
 * SQLite was creating a transaction, running the statement, and the committing the changes
 * JDBC Connection auto commits by default. When we turn it off, SQLite stopped auto committing but they were
 * still made as part of a transaction.
 *
 * If we close a connection before we commit any outstanding changes, the changes are rolled back.
 *
 * In JDBC:
 * We call methods from Connection class to execute transaction-related commands:
 * 1. turn off auto commit, Connection.setAutoCommit(false)
 * 2. Perform the SQL operations that form the transaction
 * 3. If no errors, call Connection.commit()
 *      If there are errors, call Connection.rollback()
 * 4. turn auto commit back on setAutoCommit(true)
 * */
