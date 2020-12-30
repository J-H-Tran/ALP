package academy.learnprogramming;

class Movie {

    private String name;

    public Movie(String name) {
        this.name = name;
    }

    public String plot() {
        return "No plot here";
    }

    public String getName() {
        return name;
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

class ForgettableMovie extends Movie {
    public ForgettableMovie() {
        super("Forgettable");
    }
    //no plot method
}

public class Main {

    public static void main(String[] args) {
/*
        int x;
        double y;
        y = Math.random();
        System.out.println(y + ": is the random number");
        y = y * 5;
        System.out.println(y + ": is the random number times 5");
        x = (int) (y + 1);
        System.out.println(x + ": is the random number times 5 plus 1 casted to int");
*/
/*
        for (int i = 0; i < 11; i++) {
            Movie movie = randomMovie();
            System.out.println("Movie #: " + i
            + " : " + movie.getName() + "\n"
            + "plot: " + movie.plot() + "\n");
        }
*/

        //Example on Covariance
        SuperClass tester = new Tester();
        SuperClass superClass = new SuperClass();
        System.out.println(tester.get());
        System.out.println(superClass.get());
    }

    public static Movie randomMovie() {
        int randNum = (int) (Math.random() * 5) + 1;
        System.out.println("Random number generated was: " + randNum);

        switch (randNum) {
            case 1:
                return new Jaws();
            case 2:
                return new IndependenceDay();
            case 3:
                return new MazeRunner();
            case 4:
                return new StarWars();
            case 5:
                return new ForgettableMovie();
            default:
                return null;
        }
    }

}
