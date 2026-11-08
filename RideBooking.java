import java.util.ArrayList;
import java.util.Scanner;

abstract class Ride {
    private String passengerName;
    private double distance;   

    public Ride(String passengerName, double distance) {
        this.passengerName = passengerName;
        this.distance = distance;
    }

    public String getPassengerName() { return passengerName; }
    public double getDistance() { return distance; }

    public abstract String getRideType();
    public abstract double calculateFare();
}

class BikeRide extends Ride {
    public BikeRide(String passengerName, double distance) {
        super(passengerName, distance);
    }

    public String getRideType() { return "Bike"; }

    public double calculateFare() { return 50 + getDistance() * 20; }
}

class RickshawRide extends Ride {
    public RickshawRide(String passengerName, double distance) {
        super(passengerName, distance);
    }

    public String getRideType() { return "Rickshaw"; }

    public double calculateFare() { return 70 + getDistance() * 30; }
}

class CarRide extends Ride {
    public CarRide(String passengerName, double distance) {
        super(passengerName, distance);
    }

    public String getRideType() { return "Car"; }

    public double calculateFare() { return 100 + getDistance() * 45; }
}

public class RideBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Ride activeRide = null;
        ArrayList<Ride> rideHistory = new ArrayList<>();

        while (true) {
            System.out.println("\n--- CODECAB SERVICE ---");
            System.out.println("1. Bike");
            System.out.println("2. Rickshaw");
            System.out.println("3. Car");
            System.out.println("4. Cancel Ride");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            if (choice == 0) {
                System.out.println("\n--- RIDE HISTORY ---");
                for (Ride r : rideHistory) {
                    System.out.println(r.getPassengerName() + " - " + r.getRideType() + " - Rs. " + r.calculateFare());
                }
                System.out.println("Thank you for visiting CODECAB. We hope to serve you again soon.");
                break;
            }

            if (choice < 0 || choice > 4) {
                System.out.println("Galat choice!");
                continue;
            }

            if (choice == 4) {
                if (activeRide == null) {
                    System.out.println("\nDear Passenger,");
                    System.out.println("You have no active ride to cancel.");
                } else {
                    System.out.println("\n--- RIDE CANCELLED ---");
                    System.out.println("Passenger: " + activeRide.getPassengerName());
                    System.out.println("Ride Type: " + activeRide.getRideType());
                    System.out.println("\nDear Passenger,");
                    System.out.println("Your ride has been cancelled successfully. We hope to serve you again soon.");
                    activeRide = null;
                }
                continue;
            }

            System.out.print("Passenger name: ");
            String name = sc.next();
            System.out.print("Distance (km): ");
            double distance = sc.nextDouble();

            if (choice == 1) {
                activeRide = new BikeRide(name, distance);
            } else if (choice == 2) {
                activeRide = new RickshawRide(name, distance);
            } else {
                activeRide = new CarRide(name, distance);
            }

            rideHistory.add(activeRide);

            System.out.println("\n--- RIDE BOOKED ---");
            System.out.println("Passenger: " + activeRide.getPassengerName());
            System.out.println("Ride Type: " + activeRide.getRideType());
            System.out.println("Distance : " + activeRide.getDistance() + " km");
            System.out.println("Fare     : Rs. " + activeRide.calculateFare());

            System.out.println("\nDear Passenger,");
            System.out.println("Your ride booking has been successfully confirmed. Thank you for choosing our service.");
        }

        sc.close();
    }
}
