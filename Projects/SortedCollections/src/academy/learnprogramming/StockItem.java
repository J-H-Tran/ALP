package academy.learnprogramming;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;  // could've been initialized later

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;     // or here but usually you wouldn't do both
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price > 0.0) {
            this.price = price;
        }
    }

    public void adjustStock(int quantity) { // increases OR decreases amount
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StockItem stockItem = (StockItem) o;
//        return name.equals(stockItem.name);
//    }

    // because we use the name data member when overriding equals() & hashCode() we can safely use it as a key in our Collections
    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");

        // pass equals comparison if obj is same
        if (obj == this) {
            return true;
        }

        // fail equals comparison if obj to be compared is null or not of the same class, won't compare to subclass
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 27;
    }


    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");

        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.name.compareTo(o.getName());    // String default compareTo()
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + " costs $" + String.format("%.2f", this.price);
    }
}
