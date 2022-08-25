import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class HelloWorld {
	public static void main(String[] args) {
		if (args.length > 0)
		System.out.println("Hello  " + args[0]);

		if (args[0].equals("server")) {
			try (ServerSocket server = new ServerSocket(8080)) {
				while (server.isBound()) {
					try (Socket socket = server.accept();
						Scanner scanner = new Scanner(socket.getInputStream());
						PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
					) {
						while (scanner.hasNextLine()) {
							String name = scanner.nextLine();
							System.out.println("Client sends name: " + name);
							writer.println(name);
						}
					}
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}

		if (args.length == 0)
			System.err.println("Hello, World!");
	}
}
