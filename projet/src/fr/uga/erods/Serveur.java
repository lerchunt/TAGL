package fr.uga.erods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Serveur extends Thread{
	private Socket socket = null;

	public Serveur(Socket socket) {
		super("Serveur");
		this.socket = socket;
	}

	public void run() {

		try (
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()));
				) {
			String inputLine, outputLine, str;
			int state = 0;
			ServerProtocol sp = new ServerProtocol();
			outputLine = sp.processInput("r",state);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {			
				str = in.readLine();
				outputLine = sp.processInput(str,state);
				state = sp.getState();
				out.println(outputLine);
				if(outputLine != null){
					if (outputLine.equals("q") || outputLine.equals("Q"))
						break;
				}
			}
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
