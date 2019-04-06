package socket.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

	public static void main(String[] args) throws IOException {
		int port = 4444;
		ServerSocket serverSocket = new ServerSocket(port);
		
		System.out.println("Servidor rodando na porta " + port);
		
		while(true) {
			Socket socket = serverSocket.accept();
			
			new SocketRobertina(socket).segredoDoSucesso();
		}

	}

}
