public abstract class ShipmentOrder implements SummaryPrintable
{
    protected String orderNumber;
    protected String customerName;
    protected double distanceKm;
    protected double baseFee;
    protected boolean insured;
    protected double lastCalculatedPrice;
}
public ShipmentOrder(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured)
{
    this.orderNumber=orderNumber;
    this.customerName=customerName;
    this.distanceKm=distanceKm;
    this.baseFee=baseFee;
    this.insured=insured;
}
public final void processOrder()
{
    double price=calculateBasePrice();
    price+=calculateAdditionalFee();
    if (insured)
    {
        price+=price*0.07;
    }
    lastCalculatedPrice=price;
    System.out.println(buildSummaryLine());
}
public String buildSummaryLine()
{
    return orderNumber + " " + customerName + " " +lastCalculatedPrice;
}
protected abstract double calculateBasePrice();
protected abstract double calculateAdditionalFee();
public abstract String getShipmentType();
}
