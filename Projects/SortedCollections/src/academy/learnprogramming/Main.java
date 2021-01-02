package academy.learnprogramming;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);
        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);
        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);
        temp = new StockItem("cup", .50, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", .45, 7);
        stockList.addStock(temp);
        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);
        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);
        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);
        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);
        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for (String s : stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket myBasket = new Basket("JT");
        sellItem(myBasket, "car", 1);
        System.out.println(myBasket);

        sellItem(myBasket, "car", 1);
        System.out.println(myBasket);

        if(sellItem(myBasket, "car", 1) == 0) {
            System.out.println("\nNo more cars in stock");
        }
        sellItem(myBasket, "spanner", 5);
        System.out.println(myBasket+"\n");

        sellItem(myBasket, "juice", 4);
        sellItem(myBasket, "cup", 12);
        sellItem(myBasket, "bread", 1);
        System.out.println(myBasket);

        System.out.println(stockList);

//        temp = new StockItem("pen", 1.12);
//        stockList.Items().put(temp.getName(), temp); // throws error since we're attempting to modify an unmodifiable map returned from stockList
        stockList.Items().get("car").adjustStock(2000);
        stockList.get("car").adjustStock(-1000);

        System.out.println(stockList);

        for (Map.Entry<String, Double> pricesList : stockList.PriceList().entrySet()) {
            System.out.println(pricesList.getKey() + " costs $" + String.format("%.2f", pricesList.getValue()));
        }
    }

    public static int sellItem(Basket basket, String item, int quantity) {
        // retrieve item from stockList first
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("\nWe do not sell " + item + "s");
            return 0;
        }
        if (stockList.sellStock(item, quantity) != 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}
