public class Stock {
    private String name;
    private double currentPrice;
    private final double startPrice;

    Stock(String name, int price){
        this.name = name;
        this.currentPrice = price;
        this.startPrice = price;
    }

    public double getStartPrice(){
        return startPrice;
    }
    public double getCurrentPrice(){
        return currentPrice;
    }

    public void setPrice(double price){
        this.currentPrice = price;
    }

    public String getName(){
        return name;
    }
}
