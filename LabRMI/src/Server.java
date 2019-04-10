import java.net.MalformedURLException;
import java.rmi.*;

public class Server {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		RMIServer es = new RMIServer();
		Naming.rebind("rmi://localhost/InterfaceRMI", es);

	}

}
