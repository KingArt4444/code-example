import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {
    public Server() {}
    // реализация интерфейса
    public String sayHello(String str) {
        String[] subStr;
        String delimeter = " ";
        subStr = str.split(delimeter);
        String newStr = "";
        for(int i = 0; i < subStr.length; i++) {
          subStr[i] = subStr[i].substring(0, 1).toUpperCase() + subStr[i].substring(1);
          if(i == 0)
          newStr = newStr + subStr[i];
          else
              newStr = newStr + " " + subStr[i];
        }
        return newStr;
    }
    public static void main(String args[]) {
        try {
// создаем и экспортируем удаленный объект
            Server obj = new Server();
            Hello stub =(Hello) UnicastRemoteObject.exportObject(obj, 0);
// Регистрируем удаленный объект в RMI-регистраторе под именем
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
