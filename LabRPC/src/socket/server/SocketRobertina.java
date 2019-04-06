package socket.server;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.*;

public class SocketRobertina {
	
	private final Socket socket;

	public SocketRobertina(Socket socket) {
		this.socket = socket;
	}

	public void segredoDoSucesso() throws IOException {
		
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		double q_0 = in.readDouble();
		double q_f = in.readDouble();
		int t = in.readInt();
		
		double i = Math.pow((q_f/q_0), (1/(double)t)) - 1;
		
		out.writeDouble(i);
		
		in.close();
		out.close();
		socket.close();
		
		
	}

}
