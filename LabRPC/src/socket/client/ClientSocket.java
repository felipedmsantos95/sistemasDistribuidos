package socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocket {

	public double segredoDoSucesso(double parseDouble, double parseDouble2, int parseInt, String ip) throws UnknownHostException, IOException {
		Socket socket = new Socket(ip,4444);
		
		if((parseDouble > 0) && (parseDouble2 > 0) 
				&& (parseInt > 0) && (parseDouble2 > parseDouble)) {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			out.writeDouble(parseDouble);
			out.writeDouble(parseDouble2);
			out.writeInt(parseInt);
			
			double sucesso = in.readDouble();
			
			
			in.close();
			out.close();
			socket.close();
			
			
			return sucesso;
		}
		else {
			return -1;
		}
			
		
	}

}
