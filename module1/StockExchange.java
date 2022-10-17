import java.util.LinkedList;

public class StockExchange {

    public static void main(String... args) {
        LinkedList<Stock> stocksExchange= new LinkedList<>();

        Semaphore semaphore = new Semaphore();

        Stock exchangeStock1 = new Stock("Harley-Davidson", 100);
        Stock exchangeStock2 = new Stock("Hewlett-Packard", 100);
        Stock exchangeStock3 = new Stock("Intel Corp.", 100);

        stocksExchange.add(exchangeStock1);
        stocksExchange.add(exchangeStock2);
        stocksExchange.add(exchangeStock3);
        
        LinkedList<Stock> broker1Stocks = new LinkedList<>();
        LinkedList<Stock> broker2Stocks = new LinkedList<>();
        LinkedList<Stock> broker3Stocks = new LinkedList<>();

        Stock stock1 = new Stock("Apple Inc", 100);
        Stock stock2 = new Stock("Tesla Motors Inc", 100);
        Stock stock3 = new Stock("Amazon.com Inc", 50);
        Stock stock4 = new Stock("Adobe Systems Inc", 150);
        Stock stock5 = new Stock("Airbus Group SE", 100);
        Stock stock6 = new Stock("Cisco Systems", 200);
        Stock stock7 = new Stock("Walt Disney Co", 400);

        broker1Stocks.add(stock1);
        broker1Stocks.add(stock2);
        broker1Stocks.add(stock3);
        broker1Stocks.add(stock4);
        broker2Stocks.add(stock5);
        broker2Stocks.add(stock6);
        broker2Stocks.add(stock7);

        Broker broker1 = new Broker(stocksExchange,broker1Stocks, "James", semaphore);
        Broker broker2 = new Broker(stocksExchange,broker2Stocks, "Sara", semaphore);

        broker1.start();
        broker2.start();


    }

}
