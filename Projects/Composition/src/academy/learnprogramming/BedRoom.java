package academy.learnprogramming;

public class BedRoom {

    private String name;
    private Wall wall1;
    private Wall wall2;
    private Wall wall3;
    private Wall wall4;
    private CeilingFan ceilingFan;
    private Closet closet;
    private SleepingBed sleepingBed;
    private Window window;
    private WorkDesk workDesk;

    public BedRoom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, CeilingFan ceilingFan, SleepingBed sleepingBed, Window window) {
        this.name = name;
        this.wall1 = wall1;
        this.wall2 = wall2;
        this.wall3 = wall3;
        this.wall4 = wall4;
        this.ceilingFan = ceilingFan;
        this.sleepingBed = sleepingBed;
        this.window = window;
    }

    public CeilingFan getCeilingFan() {
        return this.ceilingFan;
    }

    public void makeBed() {
        System.out.println("Bedroom -> making bed");
        sleepingBed.make();
    }
}
