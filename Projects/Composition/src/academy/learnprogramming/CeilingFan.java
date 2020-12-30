package academy.learnprogramming;

public class CeilingFan {

    private int fanColors;
    private int fanSpeed;

    public CeilingFan(int fanColors, int fanSpeed) {
        this.fanColors = fanColors;
        this.fanSpeed = fanSpeed;
    }

    public void turnOn() {
        System.out.println("Fan -> turns on");
    }

    public int getFanColors() {
        return fanColors;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }
}
