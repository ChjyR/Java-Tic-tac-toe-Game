import java.io.*;
import java.net.*;

/**
 * 
 */

/**
 * This class implement server
 * @author Chen Jingyan
 *
 */
public class Server {
	
	ServerSocket serverSock;
	public Server(ServerSocket serverSocket) {
		this.serverSock = serverSocket;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSock = new ServerSocket(5001);
		Server server = new Server(serverSock);
		server.go();

	}
	/**
     * Main Function: connect to the client
     */
	public void go() {
		try {
				while (!serverSock.isClosed()) {
					Socket socket = serverSock.accept();
					ClientHandler clientHandler = new ClientHandler(socket);
					Thread thread = new Thread(clientHandler);
					thread.start();
				}

		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

}
