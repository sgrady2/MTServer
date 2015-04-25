import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
	private static final int PORT = 12345;
	
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getLocalHost();
			Socket socket = new Socket(address, PORT);
			
			System.out.println("connected!");
			
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();
			
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			
			outputStream.writeUTF("Client says hi!");
			outputStream.flush();
			
			String message = inputStream.readUTF();
			
			System.out.println("Client received: " + message); 
			
			inputStream.close();
			outputStream.close();
			socket.close();			

			System.out.println("shutting down");		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}