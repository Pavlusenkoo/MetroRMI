import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class MetroCardServer {
    private static MetroCardSystemImpl obj;

    public static void main(String[] args) {
        try {
            obj = new MetroCardSystemImpl();
            MetroCardSystemInterface stub = (MetroCardSystemInterface) UnicastRemoteObject.exportObject(obj, 0);

            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            String name = "MetroCardSystem";
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("Server is running...");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    UnicastRemoteObject.unexportObject(obj, true);
                } catch (NoSuchObjectException e) {
                    System.err.println("No such object: " + e.getMessage());
                }
            }));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter 'stop' to stop the server: \n");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("stop")) {
                    System.out.println("Stopping server...");
                    System.exit(0); // This will trigger the shutdown hook
                    break;
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("Server exception: \n" + e.toString());
            e.printStackTrace();
        }
    }
}