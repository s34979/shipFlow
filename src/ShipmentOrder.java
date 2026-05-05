public abstract class ShipmentOrder implements SummaryPrintable {

    protected String orderNumber;
    protected String customerName;
    protected double distanceKm;
    protected double baseFee;
    protected boolean insured;
    protected double lastCalculatedPrice;

    public ShipmentOrder(String orderNumber, String customerName, double distanceKm, double baseFee, boolean insured) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.distanceKm = distanceKm;
        this.baseFee = baseFee;
        this.insured = insured;
        this.lastCalculatedPrice = 0.0;
    }

    public final void processOrder() {
        validateOrder();
        validateSpecificRules();

        double price = calculateBasePrice();
        price += calculateAdditionalFee();
        price = applyInsurance(price);
        price = applyBusinessDiscount(price);

        lastCalculatedPrice = price;
        printProcessingResult();
    }

    private void validateOrder() {
        if (orderNumber == null || orderNumber.isEmpty()) {
            throw new IllegalArgumentException("Order number cannot be empty");
        }

        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }

        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }

        if (baseFee < 0) {
            throw new IllegalArgumentException("Base fee cannot be negative");
        }
    }

    protected void validateSpecificRules() {
        // Hook method - subclasses may override if needed
    }

    private double applyInsurance(double price) {
        if (insured) {
            return price + price * 0.07;
        }
        return price;
    }

    protected double applyBusinessDiscount(double price) {
        return price;
    }

    private void printProcessingResult() {
        System.out.println(buildSummaryLine());
    }

    @Override
    public String buildSummaryLine() {
        return orderNumber + " | " + customerName + " | " + getShipmentType() + " | " + lastCalculatedPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public double getBaseFee() {
        return baseFee;
    }

    public boolean isInsured() {
        return insured;
    }

    public double getLastCalculatedPrice() {
        return lastCalculatedPrice;
    }

    protected abstract double calculateBasePrice();

    protected abstract double calculateAdditionalFee();

    public abstract String getShipmentType();
}