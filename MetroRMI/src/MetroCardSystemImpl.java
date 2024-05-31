import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class MetroCardSystemImpl implements MetroCardSystemInterface {
    private static final long serialVersionUID = 1L;
    private Map<String, Double> cardBalances;

    public MetroCardSystemImpl() throws RemoteException {
        super();
        cardBalances = new HashMap<>();
    }

    @Override
    public void issueNewCard(String studentId) throws RemoteException {
        cardBalances.putIfAbsent(studentId, 0.0);
        System.out.println("Create new card for student: " + studentId);
    }

    @Override
    public String getStudentInfo(String studentId) throws RemoteException {
        if (cardBalances.containsKey(studentId)) {
            return "Student ID: " + studentId;
        } else {
            return "Student with ID " + studentId + " not found";
        }
    }

    @Override
    public void rechargeCard(String studentId, double amount) throws RemoteException {
        if (cardBalances.containsKey(studentId)) {
            double newBalance = cardBalances.get(studentId) + amount;
            cardBalances.put(studentId, newBalance);
            System.out.println("Recharged card for student " + studentId + " with " + amount + " units");
        } else {
            System.out.println("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public void payForRide(String studentId, double amount) throws RemoteException {
        if (cardBalances.containsKey(studentId)) {
            double currentBalance = cardBalances.get(studentId);
            if (currentBalance >= amount) {
                double newBalance = currentBalance - amount;
                cardBalances.put(studentId, newBalance);
                System.out.println("Paid " + amount + " units for ride (student " + studentId + ")");
            } else {
                System.out.println("Insufficient balance for student " + studentId);
            }
        } else {
            System.out.println("Student with ID " + studentId + " not found");
        }
    }

    @Override
    public double getCardBalance(String studentId) throws RemoteException {
        if (cardBalances.containsKey(studentId)) {
            return cardBalances.get(studentId);
        } else {
            System.out.println("Student with ID " + studentId + " not found");
            return 0.0;
        }
    }
}