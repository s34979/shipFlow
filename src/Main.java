public class Main 

{

    public static void main(String[] args) {

        DomesticCourierShipment order =
                new DomesticCourierShipment(
                        "ORD1",
                        "Vedika",
                        350,
                        50,
                        true,
                        10,
                        true
                );

        order.processOrder();

        System.out.println(order.buildSummaryLine());
    }
}