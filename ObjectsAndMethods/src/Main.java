public class Main {
    public static void main(String[] args) {
        Basket basket1 = new Basket(500);
        basket1.add("Товар 1", 100);
        basket1.add("Товар 2", 200, 2);

        Basket basket2 = new Basket(500);
        basket2.add("Товар 3", 150);
        basket2.add("Товар 4", 50);

        System.out.println("Общая стоимость всех товаров: " + Basket.getTotalCost());
        System.out.println("Общее количество всех товаров: " + Basket.getTotalCount());
        System.out.println("Средняя цена товара: " + Basket.getAverageItemCost());
        System.out.println("Средняя стоимость корзины: " + Basket.getAverageBasketCost());
    }
}
