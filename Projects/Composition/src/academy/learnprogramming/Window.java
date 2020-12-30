package academy.learnprogramming;

public class Window {

    private String style;
    private boolean isTinted;
    private int lumensRating;

    public Window(String style, boolean isTinted, int lumensRating) {
        this.style = style;
        this.isTinted = isTinted;
        this.lumensRating = lumensRating;
    }

    public String getStyle() {
        return style;
    }

    public boolean isTinted() {
        return isTinted;
    }

    public int getLumensRating() {
        return lumensRating;
    }
}
