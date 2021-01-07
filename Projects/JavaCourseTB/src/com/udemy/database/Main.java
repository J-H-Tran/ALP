package com.udemy.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        /*try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\JavaMasterclassBuchalka\\Projects\\JavaCourseTB\\testjava.db");
             Statement statement = conn.createStatement()) {*/
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\JavaMasterclassBuchalka\\Projects\\JavaCourseTB\\testjava.db");
            Statement statement = conn.createStatement();
//            conn.setAutoCommit(false); default:true

            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
            /*statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "VALUES('Jon', 123456789, 'jon@email.com')");
            statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "VALUES('Ken', 123456987, 'ken@email.com')");
            statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "VALUES('Tom', 123654789, 'tom@email.com')");
            statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "VALUES('Hal', 123456, 'hal@email.com')");*/
            statement.execute("UPDATE contacts SET phone=987654 where name='Jon'");
            statement.execute("delete from contacts where name='Tom'");

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }
}
/*JDBC API (Java Database Connectivity) - consists of 2 packages: java.sql (Core JDBC) & javax.sql (Optional JDBC)
 * APIs in javax.sql package are required when working with DB servers.
 * can work with DBs, spreadsheets, and flat files.
 * Written in Java but consists of thin Java layer that calls code written in other languages.
 *
 * JDBC acts as a middleman between a Java app and a data source.
 * To use a particular data source from an app we need the JDBC driver for the data source
 * Here, to access an SQLite DB from an app we need an SQLite JDBC Driver.
 *
 * the driver is simply a Java library containing classes that implement the JDBC API
 * Because all JDBC drivers have to implement the same interfaces, it's not difficult
 * to change the data source an app uses.
 * ie. switching over to MySQL DB requires using the MySQL JDBC Driver instead of the
 * SQLite Driver. (In addition to migrating all the data from SQLite to MySQL Server)
 *
 * Not that simple though, nothing is 100% portable. If we write our JDBC code with
 * that thought in mind that we may change DB later. We can make it easier by
 * avoiding the use of DB-specific SQL and behaviours wherever possible.
 * As long as there's a driver for working with spreadsheets and flat files then working
 * with those files is fine if we want to. Can always write our own JDBC Driver if we want.
 *
 * All popular DBs provide JDBC drivers. JDK ships with a DB called Derby, which can be used for
 * desktop applications, or when prototyping. Derby JDBC driver included in JDK
 *
 * Connection String:
 * All JDBC Drivers need a connection string that's used to connect to the DB, starts with, 'jdbc:'
 * can also specify DB attributes with the connection string. Check JDBC Driver documentation to
 * see the connection string requirements.
 *
 * 2 ways of establishing connection using JDBC 4.0 - Connection String & Data Source objects
 * the latter is sometimes preferred because it allows advanced features like connection pooling
 * and distributed transactions. It's also more portable because of the way connections are established.
 * Only really needed when working with large enterprise applications like JEE.
 *
 * Whenever we want to use SQL with JDBC we use what's called Statement Objects
 * To create a Table we call connection.create() statement method
 *
 * The JDBC Connection class commits any changes we make to the DB for us immediately after
 * a statement is executed. Though, depending on the DB you're working with what what type of
 * connection you sometimes have to explicitly commit any changes you make to the DB for those
 * changes to persist. If you don't commit them before closing the connection then any changes
 * we make will be lost, rolled back.
 * */