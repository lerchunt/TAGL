package main.java.fr.uga.erods;

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
            String inputLine, outputLine;
            int state = 0;
            ServerProtocol sp = new ServerProtocol();
            outputLine = sp.processInput(null,state);
            out.println(outputLine);
 
            while ((inputLine = in.readLine()) != null) {
            	state = sp.getState();
            	if(sp.processInput(inputLine,state) != null){
            		outputLine = sp.processInput(inputLine,state);
                	out.println(outputLine);
            	}
            	
                if (outputLine.equals("EXIT"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
