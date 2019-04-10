import java.rmi.*;


public interface InterfaceRMI extends Remote {
	public void setString (String s) throws RemoteException;
	public String getString() throws RemoteException;

}
