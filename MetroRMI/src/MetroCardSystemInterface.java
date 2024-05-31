import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MetroCardSystemInterface extends Remote {
    void issueNewCard(String studentId) throws RemoteException;
    String getStudentInfo(String studentId) throws RemoteException;
    void rechargeCard(String studentId, double amount) throws RemoteException;
    void payForRide(String studentId, double amount) throws RemoteException;
    double getCardBalance(String studentId) throws RemoteException;
}