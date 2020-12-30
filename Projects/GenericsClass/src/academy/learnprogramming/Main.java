package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        // how to only add the right type of player to a team without creating new kinds of teams? modify Team to be a Generic class
//        Team adelaideCrows = new Team("AdelaideCrows"); // when Team became Type Parameterized this became unchecked raw type warning
        Team<FootballPlayer> adelaideCrows = new Team<>("AdelaideCrows"); // this instance of team now only accepts FootballPlayer types only
        adelaideCrows.addPlayer(joe);
//        adelaideCrows.addPlayer(pat);
//        adelaideCrows.addPlayer(beckham);

        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);

        System.out.println(adelaideCrows.numPlayers());

//        Team<String> brokenTeam = new Team<>("this won't work"); //runtime error, String cannot be cast to Player.
        // How do we restrict the type? Bounded type parameters. class declaration is now: public class Team<T extends Player>
        // we told Java that Team now accepts only Types that extends Player and it's subclasses. Player is the upperbound
        // as a consequence of that we can remove the 'ugly' solution of (Player) type casting. getName() now knows it expects only type Players now bounded
//        brokenTeam.addPlayer("no-one");

        Team<SoccerPlayer> brokenTeam = new Team<>("this won't work");
        brokenTeam.addPlayer(beckham);

        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        FootballPlayer banks = new FootballPlayer("Gordon");
        melbourne.addPlayer(banks);

        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        hawthorn.matchResult(fremantle, 1, 0);
        hawthorn.matchResult(adelaideCrows, 3, 8);

        adelaideCrows.matchResult(fremantle, 1, 0);
//        adelaideCrows.matchResult(baseballTeam, 1, 0); // Now throws an error because adeliade crows and baseball team are different object types

        System.out.println("\n\nRankings:");
        System.out.println(adelaideCrows.getName() + ": " + adelaideCrows.ranking());
        System.out.println(melbourne.getName() + ": " + melbourne.ranking());
        System.out.println(fremantle.getName() + ": " + fremantle.ranking());
        System.out.println(hawthorn.getName() + ": " + hawthorn.ranking());

        /*
        * But now how do we compare the Teams? We'll need to modify the Team class to implement the interface Comparable
        * */

        System.out.println("\n\nTeam comparisons:");
        System.out.println(adelaideCrows.compareTo(melbourne));
        System.out.println(adelaideCrows.compareTo(hawthorn));
        System.out.println(hawthorn.compareTo(adelaideCrows));
        System.out.println(melbourne.compareTo(fremantle));

    }
}
