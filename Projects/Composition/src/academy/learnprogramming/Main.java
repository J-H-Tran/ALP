package academy.learnprogramming;

public class Main {

    public static void main(String[] args) {

        Wall wall1 = new Wall("North");
        Wall wall2 = new Wall("East");
        Wall wall3 = new Wall("South");
        Wall wall4 = new Wall("West");

        CeilingFan fan = new CeilingFan(2, 5);

        SleepingBed bed = new SleepingBed("low", 1, 6, 1);

        Window window = new Window("large", false, 10);

        BedRoom bedRoom = new BedRoom("Jonathan", wall1, wall2, wall3, wall4, fan, bed, window);

        bedRoom.makeBed();
        bedRoom.getCeilingFan().turnOn();
    }
}
