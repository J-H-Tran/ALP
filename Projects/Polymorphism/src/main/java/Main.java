package com.codeacademy.oop;

class Movie {

    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot() {
        return "No plot here";
    }
}

class Jaws extends Movie {
    public Jaws() {
        super("Jaws");
    }

    public String plot() {
        return "A shark eats a lot of people";
    }
}

class IndependenceDay extends Movie {
    public IndependenceDay() {
        super("Independence Day");
    }

    @Override
    public String plot() {
        return "Aliens attempt to take over Earth";
    }
}

class MazeRunner extends Movie {
    public MazeRunner() {
        super("Maze Runner");
    }

    @Override
    public String plot() {
        return "Kids escape maze";
    }
}

class StarWars extends Movie {
    public StarWars() {
        super("Star Wars");
    }

    @Override
    public String plot() {
        return "Imperials try to take over galaxy";
    }
}

class forgettableMovie extends Movie {
    public forgettableMovie() {
        super("Forgettable");
    }
    //no plot method
}

public class Main {

    public static void main(String[] args) {

    }

    public static void randomMovie() {
        int randNum = (int) (Math.random() * 5) + 1;

    }


}
