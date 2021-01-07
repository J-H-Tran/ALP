package com.udemy.database;

public class Main {

    public static void main(String[] args) {

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
 * */