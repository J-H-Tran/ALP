package com.udemy.database;

import com.udemy.model.Datasource;

public class Main2 {

    public static void main(String[] args) {
        Datasource datasaource = new Datasource();

        if (!datasaource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        datasaource.close();
    }
}


