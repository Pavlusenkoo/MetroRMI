import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MetroCardClient {
    public static void main(String[] args) {
        try {
            String name = "MetroCardSystem";
            Registry registry = LocateRegistry.getRegistry();
            MetroCardSystemInterface obj = (MetroCardSystemInterface) registry.lookup(name);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();

            obj.issueNewCard(studentId);
            System.out.println("New card issued for student: " + studentId);

            System.out.print("Enter amount to recharge: ");
            double rechargeAmount = scanner.nextDouble();
            scanner.nextLine();

            obj.rechargeCard(studentId, rechargeAmount);
            System.out.println("Card recharged for student " + studentId + " with " + rechargeAmount + " units");

            double balance = obj.getCardBalance(studentId);
            System.out.println("Card balance for student " + studentId + ": " + balance);

            System.out.print("Enter amount to pay for ride: ");
            double rideAmount = scanner.nextDouble();
            scanner.nextLine();

            obj.payForRide(studentId, rideAmount);

            balance = obj.getCardBalance(studentId);
            System.out.println("Card balance for student " + studentId + " after ride payment: " + balance);

            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}