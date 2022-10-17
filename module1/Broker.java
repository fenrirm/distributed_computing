import java.util.LinkedList;
import java.util.Random;

public class Broker extends Thread{
    private final Random random = new Random();
    private LinkedList<Stock> stocksExchange;
    private LinkedList<Stock> brokerStocks;
    private boolean stop;
    private Semaphore semaphore;

    Broker(LinkedList<Stock> stocksExchange, LinkedList<Stock> brokerStocks, String name, Semaphore semaphore){
        super(name);
        this.stocksExchange = stocksExchange;
        this.brokerStocks = brokerStocks;
        this.stop = false;
        this.semaphore = semaphore;

    }

    public double getAverageStartSum(){
        double sum1=0, sum2=0, i=0, j=0;
        for (Stock value : stocksExchange) {
            sum1 = sum1 + value.getStartPrice();
            i++;
        }
        for (Stock value : brokerStocks) {
            sum2 = sum2 + value.getStartPrice();
            j++;
        }

        return (sum1+sum2)/(i+j);
    }

    public double getAverageCurrentSum(){
        double  sum1=0, sum2=0, i=0, j=0;
        for (Stock value : stocksExchange) {
            sum1 = sum1 + value.getCurrentPrice();
            i++;
        }
        for (Stock value : brokerStocks) {
            sum2 = sum2 + value.getCurrentPrice();
            j++;
        }
        return (sum1+sum2)/(i+j);

    }

    public double getDifference(){
        return getAverageStartSum()-getAverageCurrentSum();
    }

    public void sellStock(Stock stock){
        semaphore.toFalse();
        if(getDifference()>getAverageStartSum()*0.25){
            stop = true;
            System.out.println("exchanging is over");
        }
        else {
            stock.setPrice(stock.getCurrentPrice() - stock.getCurrentPrice() * 0.2);
            System.out.println(Thread.currentThread().getName()+" is selling");
            System.out.println("---"+stock.getName()+" - "+stock.getCurrentPrice());
            System.out.println("current index after selling - "+getAverageCurrentSum());
        }
        semaphore.toTrue();
    }

    public void buyStock(Stock stock){
        semaphore.toFalse();
        if(getDifference()>getAverageStartSum()*0.1){
            stop = true;
            System.out.println("exchanging is over ");

        }
        else {
            stock.setPrice(stock.getCurrentPrice() + stock.getCurrentPrice() * 0.05);
            System.out.println(Thread.currentThread().getName()+" is buying");
            System.out.println("---"+stock.getName()+" - "+stock.getCurrentPrice());
            System.out.println("current index after buying - "+getAverageCurrentSum());
        }
        semaphore.toTrue();

    }

    @Override
    public void run() {
        while (!stop) {
            int index1 = random.nextInt(brokerStocks.size());
            if(semaphore.getStatus()) {
                sellStock(brokerStocks.get(index1));
            }else {
                System.out.println(Thread.currentThread().getName() + ", please wait, other broker is selling stocks right now");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int index2 = random.nextInt(stocksExchange.size());
            if(semaphore.getStatus()) {
                buyStock(stocksExchange.get(index2));
            }else {
                System.out.println(Thread.currentThread().getName() + ", please wait, other broker is buying stocks right now");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*int index3 = random.nextInt(brokerStocks.size());
            if(semaphore.getStatus()) {
                System.out.println(Thread.currentThread().getName()+" can sell stocks as nobody is selling stocks right now");
                sellStock(brokerStocks.get(index3));
            }else {
                System.out.println(Thread.currentThread().getName() + ", please wait, other broker is selling stocks right now");
            }*/

        }
    }
}
