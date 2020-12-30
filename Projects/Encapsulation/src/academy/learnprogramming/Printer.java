package academy.learnprogramming;

import java.util.Objects;

public class Printer {

    private int tonerLevel;
    private int numberOfPagesPrinted;
    private boolean isDuplex;

    public Printer(int tonerLevel, boolean isDuplex) {

        if(tonerLevel > -1 && tonerLevel <= 100) {
            this.tonerLevel = tonerLevel;
        } else {
            this.tonerLevel = -1;
        }
        this.numberOfPagesPrinted = 0;
        this.isDuplex = isDuplex;
    }

    public int fillUpToner(int tonerAmount) {

        if(tonerAmount > 0 && tonerAmount <= 100) {

            if(this.tonerLevel + tonerAmount > 100) {
                return -1;
            }
            this.tonerLevel += tonerAmount;
            return this.tonerLevel;
        } else return -1;
    }

    public int checkTonerLevel() {
        return this.tonerLevel;
    }

    public int printPage(int pages) {

        int pagesToPrint = pages;
        if(this.isDuplex) {
            pagesToPrint = (pages / 2) + (pages % 2);
            System.out.println("Printing in duplex mode.");
        }
        this.numberOfPagesPrinted += pagesToPrint;
        return pagesToPrint;
    }

    public String isPrinterDuplex() {

        if(isDuplex == true) {
            return "Is a Duplex Printer";
        }
        return "Not a Duplex Printer.";
    }

    public int getTonerLevel() {
        return tonerLevel;
    }

    public void setTonerLevel(int tonerLevel) {
        this.tonerLevel = tonerLevel;
    }

    public int getNumberOfPagesPrinted() {
        return numberOfPagesPrinted;
    }

    public void setNumberOfPagesPrinted(int numberOfPagesPrinted) {
        this.numberOfPagesPrinted = numberOfPagesPrinted;
    }

    public boolean isDuplex() {
        return isDuplex;
    }

    public void setDuplex(boolean duplex) {
        isDuplex = duplex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Printer printer = (Printer) o;
        return tonerLevel == printer.tonerLevel &&
                numberOfPagesPrinted == printer.numberOfPagesPrinted &&
                isDuplex == printer.isDuplex;
    }

    @Override
    public String toString() {
        return "Printer{" +
                "tonerLevel=" + tonerLevel +
                ", numberOfPagesPrinted=" + numberOfPagesPrinted +
                ", isDuplex=" + isDuplex +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(tonerLevel, numberOfPagesPrinted, isDuplex);
    }

}
