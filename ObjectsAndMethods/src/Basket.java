public class Basket {
    private static int totalCount = 0;
    private static int totalCost = 0;

    private String items = "";
    private int totalPrice = 0;
    private final int limit;

    public Basket(int i) {
        totalCount++;
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static int getTotalCost() {
        return totalCost;
    }

    public static void increaseTotalCount(int count) {
        totalCount += count;
    }

    public static void increaseTotalCost(int cost) {
        totalCost += cost;
    }

    public static double getAverageItemCost() {
        if (totalCount == 0) {
            return 0;
        }
        return (double) totalCost / totalCount;
    }

    public static double getAverageBasketCost() {
        if (totalCount == 0) {
            return 0;
        }
        return (double) totalCost / getTotalBasketCount();
    }

    public static int getTotalBasketCount() {
        return totalCount;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = contains(name);

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occurred :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
        increaseTotalCount(count);
        increaseTotalCost(count * price);
    }

    public void clear() {
        items = "";
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
