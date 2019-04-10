import java.rmi.*;
import java.rmi.server.*;

public class RMIServer extends UnicastRemoteObject implements InterfaceRMI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stringState;
	
	public RMIServer() throws RemoteException{};
	
	public void setString(String s) throws RemoteException{
		
		stringState = s;
		
		System.out.println("Setando a variável do objeto para '"  + s + "'");
	}
	
	public String getString() throws RemoteException{
		return stringState;
	}

}
