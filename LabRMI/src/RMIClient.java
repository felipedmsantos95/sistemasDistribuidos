import java.net.MalformedURLException;
import java.rmi.*;
public class RMIClient {

	public static void main(String[] args) {
		try {
			InterfaceRMI example = (InterfaceRMI) Naming.lookup("rmi://localhost/InterfaceRMI");
			example.setString("sucesso");
			System.out.println(example.getString());
			
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(NotBoundException e) {
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

}
