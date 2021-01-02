package academy.learnprogramming;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) { // get item if it exists in the map else put in new item so we now have it in stock (arg 2)
            StockItem inStock = list.getOrDefault(item.getName(), item);

            // If already in stock, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }

        return 0;
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);

        if ((inStock != null) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }

        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> PriceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {  // typically not done this way, mainly used for debugging
        StringBuilder s = new StringBuilder("\nStock List:\n"); // IntelliJ suggested to use StringBuilder in place of concatenating string in a loop
        double totalCost = 0.0;

        for (Map.Entry<String, StockItem> itemEntry : list.entrySet()) {
            StockItem stockItem = itemEntry.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s.append("\t").append(stockItem).append(". There are ").append(stockItem.quantityInStock())
                    .append(" in stock. Value of items: $").append(String.format("%.2f", itemValue)).append("\n");
            totalCost += itemValue;
        }

        return s + "Total stock value: $" + String.format("%.2f", totalCost);
    }
}
