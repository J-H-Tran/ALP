package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {
	/*
	* Create a generic class to implement a league table for a sport.
	* The class should allow teams to be added to the list and store a list of teams that belong to the league.
	*
	* Your class should have a method to print out the teams in order with the team at the top of the league
	* printed first.
	*
	* Only teams of the same type should be added to any particular instance of the league class.
	* The program should fail to compile if an attempt is made to add an incompatible team.
	* */

        League<Team<FootballPlayer>> footballLeague = new League<>("AFL");

        Team<FootballPlayer> adelaideCrows = new Team<>("AdelaideCrows");
        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");
        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");

        footballLeague.add(adelaideCrows);
        footballLeague.add(melbourne);
        footballLeague.add(hawthorn);
        footballLeague.add(fremantle);

//        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
//        footballLeague.add(baseballTeam);

        hawthorn.matchResult(fremantle, 1, 0);
        hawthorn.matchResult(adelaideCrows, 3, 8);
        adelaideCrows.matchResult(fremantle, 1, 0);

        footballLeague.showLeagueTable();

        /*
        * Using raw types could lead to errors at compile time, below code will still run
        * */
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team rawTeam = new Team("Raw Team");
        rawTeam.addPlayer(beckham); // unchecked warning
        rawTeam.addPlayer(pat);     // unchecked warning

        footballLeague.add(rawTeam);

        // One way of solving the issue is specifying 2 Generics Type Parameters but can be messy
//        League<FootballPlayer, Team<FootballPlayer>> rawLeagueTeam = new League<>("Raw");
        League<Team> rawLeagueTeam = new League<>("Raw");
        rawLeagueTeam.add(adelaideCrows);   // no warning
        rawLeagueTeam.add(baseballTeam);    // no warning
        rawLeagueTeam.add(rawTeam);         // no warning

        League reallyRaw = new League("Really Raw");    // this is a real issue
        reallyRaw.add(adelaideCrows);   // unchecked warning
        reallyRaw.add(baseballTeam);    // unchecked warning
        reallyRaw.add(rawTeam);         // unchecked warning





    }
}
