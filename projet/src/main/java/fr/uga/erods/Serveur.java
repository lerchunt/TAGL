package main.java.fr.uga.erods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
            Transaction sp = new Transaction();
            Menu m = new Menu();
            outputLine = m.processInput(null, sp);
            out.println(outputLine);
 
            while ((inputLine = in.readLine()) != null) {
            	state = m.getState();
            	if(m.processInput(inputLine,sp) != null){
            		outputLine = m.processInput(inputLine,sp);
                	out.println(outputLine);
            	}
            	
                if (outputLine.equals("q")||outputLine.equals("Q")){
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
