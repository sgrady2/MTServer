import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	private static final int PORT = 12345;
	
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("IP address: " + address.getHostName());
			
			System.out.println("creating server socket");

			ServerSocket serverSocket = new ServerSocket(PORT);
			
			System.out.println("waiting for connection");
			
			Socket socket = serverSocket.accept();

			System.out.println("accepted connection");

			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();
			
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			
			outputStream.writeUTF("Server says hi!");
			outputStream.flush();
			
			String message = inputStream.readUTF();
			
			System.out.println("Server received: " + message); 
			
			inputStream.close();
			outputStream.close();
			socket.close();			
			
			System.out.println("shutting down");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}