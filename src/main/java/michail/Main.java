package michail;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static final String[] directions = {"SN","SE","SW","NE","NW", "NS", "EW", "EN", "ES", "WS", "WE", "WN"};
    public static String getRandomDirection() {
        int randomIndex = (int) (Math.random() * directions.length);
        return directions[randomIndex];
    }
    public static void main(String[] args) {
        SmartTrafficControl trafficControl = new SmartTrafficControl();
        Thread carAdderThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Car car1 = new Car(getRandomDirection());
                Car car2 = new Car(getRandomDirection());
                Car car3 = new Car(getRandomDirection());
                System.out.println("Cars arrived: " + car1 + " " + car2 + " " + car3);
                trafficControl.addCar(car1);
                trafficControl.addCar(car2);
                trafficControl.addCar(car3);


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread regulatorThread = new Thread(() -> trafficControl.regulateCars());

        carAdderThread.start();
        regulatorThread.start();
    }
}